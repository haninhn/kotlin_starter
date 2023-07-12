package com.example.authstarterkotlin.feature_auth.data.remote.response

import kotlinx.serialization.Serializable

@Serializable
data class LoginResponse(
    val accessToken: String,
    val refreshToken: String
)
