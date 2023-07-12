package com.example.authstarterkotlin.feature_auth.presentation.login

data class LoginState(
    val isLoading: Boolean = false,
    val error: String = "",
    val email: String = "",
    val emailError: String? = null,
    val password: String = "",
    val passwordError: String? = null,
)