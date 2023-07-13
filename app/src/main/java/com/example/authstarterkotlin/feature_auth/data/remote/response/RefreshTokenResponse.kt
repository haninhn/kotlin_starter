package com.example.authstarterkotlin.feature_auth.data.remote.response

import kotlinx.serialization.Serializable

@Serializable
data class RefreshTokenResponse(
    val accessToken: String,
    val newRefreshToken: String
)
