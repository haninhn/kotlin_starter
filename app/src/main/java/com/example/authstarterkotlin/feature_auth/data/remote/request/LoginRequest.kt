package com.example.authstarterkotlin.feature_auth.data.remote.request
import kotlinx.serialization.Serializable

@Serializable
data class LoginRequest(
    val email : String,
    val passwor : String
)