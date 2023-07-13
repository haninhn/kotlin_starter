package com.example.authstarterkotlin.feature_auth.domain.repository
import com.example.authstarterkotlin.feature_auth.data.remote.request.RegisterRequest

interface AuthRepository {
    suspend fun login(email: String , password:String)
    suspend fun register(registerRequest: RegisterRequest)
    suspend fun validateEmail(email: String)
    suspend fun validateCode(code: String, email: String)
    suspend fun updatePassword(email: String, code: String, password: String)
    suspend fun refreshToken(): String?

}