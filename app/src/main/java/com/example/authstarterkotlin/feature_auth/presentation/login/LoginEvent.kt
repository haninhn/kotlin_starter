package com.example.authstarterkotlin.feature_auth.presentation.login
sealed class LoginEvent {
    data class EmailChanged(val email: String) : LoginEvent()
    data class PasswordChanged(val password: String) : LoginEvent()
    object SubmitBtn : LoginEvent()

}