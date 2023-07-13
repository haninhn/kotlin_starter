package com.example.authstarterkotlin.feature_auth.data.remote

import com.example.authstarterkotlin.feature_auth.data.remote.request.LoginRequest
import com.example.authstarterkotlin.feature_auth.data.remote.request.RegisterRequest
import com.example.authstarterkotlin.feature_auth.data.remote.response.LoginResponse
import com.example.authstarterkotlin.feature_auth.data.remote.response.RefreshTokenResponse
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
            setBody(loginRequest) //request body
        }.body()
    }
    suspend fun refreshToken(refreshTokenRequest: String): com.example.authstarterkotlin.core.Response<RefreshTokenResponse> {

        val requestBody = mapOf(
            "refreshToken" to refreshTokenRequest,
        )
        return httpClient.post("example.com/refreshToken") {
            contentType(ContentType.Application.Json)
            setBody(requestBody)
        }.body()
    }

    suspend fun register(registerRequest: RegisterRequest): com.example.authstarterkotlin.core.Response<Unit> {
        return httpClient.post("example.com/register") {
            contentType(ContentType.Application.Json)
            setBody(registerRequest)
        }.body()
    }

    suspend fun validateEmail(email: String): com.example.authstarterkotlin.core.Response<Unit> {
        val requestBody = mapOf(
            "email" to email,
        )
        return httpClient.post("example.com/validate-email") {
            contentType(ContentType.Application.Json)
            setBody(requestBody)
        }.body()
    }

    suspend fun validateCode(
        code: String,
        email: String
    ): com.example.authstarterkotlin.core.Response<Unit> {

        val requestBody = mapOf(
            "code" to code,
        )
        return httpClient.post("example.com/validate-code/$email") {
            contentType(ContentType.Application.Json)
            setBody(requestBody)
        }.body()
    }

    suspend fun updatePassword(email: String, code: String, password : String): com.example.authstarterkotlin.core.Response<Unit> {
        print("update password credentials $email $code $password")

        val requestBody = mapOf(
            "password" to password,
        )
        return httpClient.post("example.com/update-password/$email/$code") {
            contentType(ContentType.Application.Json)
            setBody(requestBody)
        }.body()
    }



}