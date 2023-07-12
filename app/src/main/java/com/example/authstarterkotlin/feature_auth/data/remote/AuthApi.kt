package com.example.authstarterkotlin.feature_auth.data.remote

import com.example.authstarterkotlin.feature_auth.data.remote.request.LoginRequest
import com.example.authstarterkotlin.feature_auth.data.remote.request.RegisterRequest
import com.example.authstarterkotlin.feature_auth.data.remote.response.LoginResponse
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.http.cio.*

class AuthApi(
    private val httpClient: HttpClient
) {
    suspend fun login(loginRequest: LoginRequest): com.example.authstarterkotlin.core.Response<LoginResponse> {
        return httpClient.post("example.com/login") {  //endPoint
            contentType(ContentType.Application.Json)
            setBody(loginRequest) //request content
        }.body()
    }

    suspend fun register(registerRequest: RegisterRequest): com.example.authstarterkotlin.core.Response<Unit> {
        return httpClient.post("example.com/register") {
            contentType(ContentType.Application.Json)
            setBody(registerRequest)
        }.body()
    }

}