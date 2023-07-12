package com.example.authstarterkotlin.core

import kotlinx.serialization.Serializable

@Serializable
data class Response<T>(
    val statusCode: Int? = null,
    val message: String? = null,
    val data: T? = null
)