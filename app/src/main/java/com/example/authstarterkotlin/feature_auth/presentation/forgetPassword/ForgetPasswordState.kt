package com.example.authstarterkotlin.feature_auth.presentation.forgetPassword

data class ForgetPasswordState (
    val isLoading: Boolean = false,
    val error:String = "",
    val email: String = "",
    val emailError: String = "",
    val isEmailSent: Boolean = false
)