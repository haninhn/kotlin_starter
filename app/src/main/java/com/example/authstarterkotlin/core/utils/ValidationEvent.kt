package com.example.authstarterkotlin.core.utils

sealed class ValidationEvent {
    data class Success(val message: String) : ValidationEvent()
    data class Error(val error: String) : ValidationEvent()
}