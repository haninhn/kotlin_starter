package com.example.authstarterkotlin.feature_auth.data.remote

import com.example.authstarterkotlin.core.Response
import com.example.authstarterkotlin.feature_auth.data.remote.request.LoginRequest
import com.example.authstarterkotlin.feature_auth.data.remote.request.RegisterRequest
import com.example.authstarterkotlin.feature_auth.data.remote.response.LoginResponse
import com.example.authstarterkotlin.feature_auth.data.remote.response.RefreshTokenResponse

class AuthRemoteDataSource(private var authApi: AuthApi) {
    suspend fun login(loginRequest: LoginRequest): Response<LoginResponse> {
        return authApi.login(loginRequest = loginRequest)
    }
    suspend fun register(registerRequest: RegisterRequest): Response<Unit> {
        return authApi.register(registerRequest = registerRequest)
    }

    suspend fun refreshToken(refreshTokenRequest: String): Response<RefreshTokenResponse> {
        return authApi.refreshToken(refreshTokenRequest = refreshTokenRequest)
    }

    suspend fun validateEmail(validateEmailRequest: String): Response<Unit> {
        return authApi.validateEmail(email = validateEmailRequest)
    }

    suspend fun validateCode(
        validateCodeRequest: String,
        email: String
    ): Response<Unit> {
        return authApi.validateCode(code = validateCodeRequest, email = email)
    }

    suspend fun updatePassword(
        email: String,
        code: String,
        password: String
    ): Response<Unit> {
        return authApi.updatePassword(
            email = email,
            code = code,
            password = password
        )
    }
}