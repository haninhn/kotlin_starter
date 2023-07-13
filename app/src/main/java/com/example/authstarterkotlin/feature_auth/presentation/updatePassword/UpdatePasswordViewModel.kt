package com.example.authstarterkotlin.feature_auth.presentation.updatePassword

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.authstarterkotlin.core.utils.ValidationEvent
import com.example.authstarterkotlin.core.utils.validation.validatePassword
import com.example.authstarterkotlin.core.utils.validation.validateRepeatedPassword
import com.example.authstarterkotlin.feature_auth.domain.use_case.UpdatePasswordUseCase
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class UpdatePasswordViewModel(
        private val updatePasswordUseCase: UpdatePasswordUseCase,
        savedStateHandle: SavedStateHandle,
    ) : ViewModel() {
        private val _state = mutableStateOf(UpdatePasswordState())
        val state: State<UpdatePasswordState> = _state

        private val validationEventChannel = Channel<ValidationEvent>()
        val validationEvents = validationEventChannel.receiveAsFlow()

        init {
              val email = savedStateHandle.get<String>("email")
              val code = savedStateHandle.get<String>("code")
              _state.value = state.value.copy(
                  email = email!!
              )
              _state.value = state.value.copy(
                  code = code!!
              )
        }

    fun onEvent(event: UpdatePasswordEvent) {
        when (event) {

            is UpdatePasswordEvent.EnteredPassword -> {
                _state.value = state.value.copy(password = event.password)
            }

            is UpdatePasswordEvent.EnteredConfirmPassword -> {
                _state.value = state.value.copy(confirmPassword = event.passwordConfirm)
            }

            is UpdatePasswordEvent.Submit -> {
                val passwordResult = validatePassword(_state.value.password)
                val validateRepeatedPassword = validateRepeatedPassword(_state.value.password, _state.value.confirmPassword)

                val hasError = listOf(
                    passwordResult,
                    validateRepeatedPassword,
                ).any { !it.successful }

                _state.value = passwordResult.errorMessage?.let {
                    state.value.copy(
                        passwordError = it
                    )
                }!!

                _state.value = validateRepeatedPassword.errorMessage?.let {
                    state.value.copy(
                        confirmPasswordError = it
                    )
                }!!

                if (hasError) {
                    return
                }else{
                    submitData()
                }
            }
        }
    }

    private fun submitData() {
        viewModelScope.launch {
            updatePasswordUseCase.invoke(_state.value.email, _state.value.code, _state.value.password)
                .collect { result ->
                    when (result) {
                        is Resource.Loading -> {
                            _state.value = state.value.copy(
                                isLoading = true,
                                error = ""
                            )
                        }
                        is Resource.Success -> {
                            _state.value = state.value.copy(
                                isLoading = false,
                                error = ""
                            )
                            Log.e("updatePassword", "updatePassword ${result.message}")
                        }

                        is Resource.Error -> {
                            _state.value = state.value.copy(
                                isLoading = false,
                                error = result.message!!,
                            )
                            validationEventChannel.send(ValidationEvent.Error(_state.value.error))
                        }
                        else -> {}
                    }
                }
        }
    }
}