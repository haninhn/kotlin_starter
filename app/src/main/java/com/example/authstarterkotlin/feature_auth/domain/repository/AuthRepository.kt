package com.example.authstarterkotlin.feature_auth.domain.repository
import com.example.authstarterkotlin.feature_auth.data.remote.request.LoginRequest
import com.example.authstarterkotlin.feature_auth.data.remote.request.RegisterRequest

interface AuthRepository {
    suspend fun login(email: String , passwod:String)
    suspend fun register(registerRequest: RegisterRequest)
}