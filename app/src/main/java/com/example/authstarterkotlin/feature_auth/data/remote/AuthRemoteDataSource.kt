package com.example.authstarterkotlin.feature_auth.data.remote

import com.example.authstarterkotlin.core.Response
import com.example.authstarterkotlin.feature_auth.data.remote.request.LoginRequest
import com.example.authstarterkotlin.feature_auth.data.remote.request.RegisterRequest
import com.example.authstarterkotlin.feature_auth.data.remote.response.LoginResponse

class AuthRemoteDataSource(private var authApi: AuthApi) {
    suspend fun login(loginRequest: LoginRequest): Response<LoginResponse> {
        return authApi.login(loginRequest = loginRequest)
    }
    suspend fun register(registerRequest: RegisterRequest): Response<Unit> {
        return authApi.register(registerRequest = registerRequest)
    }
}