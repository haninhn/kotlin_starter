package com.example.authstarterkotlin.feature_auth.domain.use_case

import DefaultUseCase
import Resource
import com.example.authstarterkotlin.feature_auth.data.remote.request.LoginRequest
import com.example.authstarterkotlin.feature_auth.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow

class LoginUseCase(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(email: String , password:String): Flow<Resource<Unit>> {
        return DefaultUseCase(
            onRequest = {
                authRepository.login(email, password)
            }
        ).execute()
    }
}