package com.example.authstarterkotlin.feature_auth.presentation.forgetPassword

sealed class ForgetPasswordEvent {
    data class EmailChanged(val email: String): ForgetPasswordEvent()
    object SendEmailBtN: ForgetPasswordEvent()
}