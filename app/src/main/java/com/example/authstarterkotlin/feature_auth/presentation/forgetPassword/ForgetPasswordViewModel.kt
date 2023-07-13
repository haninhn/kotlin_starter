package com.example.authstarterkotlin.feature_auth.presentation.forgetPassword

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.authstarterkotlin.core.utils.ValidationEvent
import com.example.authstarterkotlin.core.utils.validation.validateEmail
import com.example.authstarterkotlin.feature_auth.domain.use_case.ValidateEmailUseCase
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class ForgetPasswordViewModel(
    private val validateEmailUseCase: ValidateEmailUseCase,
) : ViewModel() {

    private val _state = mutableStateOf(ForgetPasswordState())
    val state: State<ForgetPasswordState> = _state
    private val validationEventChannel = Channel<ValidationEvent>()
    val validationEvents = validationEventChannel.receiveAsFlow()

    fun onEvent(event: ForgetPasswordEvent) {
        when (event) {
            is ForgetPasswordEvent.EmailChanged -> {
                _state.value = state.value.copy(email = event.email)
            }
            is ForgetPasswordEvent.SendEmailBtN -> {
                val emailResult = validateEmail(_state.value.email)

                _state.value = emailResult.errorMessage?.let {
                    state.value.copy(
                        emailError = it,
                    )
                }!!

                if (!emailResult.successful) {
                    return
                } else {
                    submitData()
                }
            }
        }
    }

   private fun submitData() {
        viewModelScope.launch {
            validateEmailUseCase.invoke(_state.value.email)
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
                            Log.e("viewModel", "login: ${result.message}")

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