package com.example.authstarterkotlin.feature_auth.presentation.updatePassword

data class UpdatePasswordState (
    val email : String = "",
    val code : String = "",
    val isLoading: Boolean = false,
    val error: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val passwordError: String = "",
    val confirmPasswordError: String = "",
)