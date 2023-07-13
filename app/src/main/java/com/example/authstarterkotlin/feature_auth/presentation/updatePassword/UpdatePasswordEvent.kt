package com.example.authstarterkotlin.feature_auth.presentation.updatePassword

sealed class UpdatePasswordEvent {
    data class EnteredPassword(val password: String) : UpdatePasswordEvent()
    data class EnteredConfirmPassword(val passwordConfirm: String) : UpdatePasswordEvent()
    object Submit : UpdatePasswordEvent()
}