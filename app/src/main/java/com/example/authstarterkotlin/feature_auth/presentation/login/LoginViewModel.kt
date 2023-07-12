package com.example.authstarterkotlin.feature_auth.presentation.login

import Resource
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.authstarterkotlin.core.utils.ValidationEvent
import com.example.authstarterkotlin.core.utils.validation.validateEmail
import com.example.authstarterkotlin.core.utils.validation.validatePassword
import com.example.authstarterkotlin.feature_auth.domain.use_case.LoginUseCase
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginUseCase: LoginUseCase,
) : ViewModel() {
    private val _state = mutableStateOf(LoginState())
    val state: State<LoginState> = _state

    private val validationEventChannel = Channel<ValidationEvent>()
    val validationEvents = validationEventChannel.receiveAsFlow()

    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.EmailChanged -> {
                _state.value = state.value.copy(email = event.email)
            }
            is LoginEvent.PasswordChanged -> {
                _state.value = state.value.copy(password = event.password)
            }

            is LoginEvent.SubmitBtn -> {

                val emailResult = validateEmail(_state.value.email)
                val passwordResult = validatePassword(_state.value.password)

                val hasError = listOf(
                    emailResult,
                    passwordResult
                ).any { !it.successful }

                _state.value = state.value.copy(
                    emailError = emailResult.errorMessage,
                )
                _state.value = state.value.copy(
                    passwordError = passwordResult.errorMessage
                )

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
            loginUseCase.invoke(_state.value.email, _state.value.password)
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
                        Log.e("login", "login: ${result.message}")

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