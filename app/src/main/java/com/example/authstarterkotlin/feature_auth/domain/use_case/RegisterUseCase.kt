package com.example.authstarterkotlin.feature_auth.domain.use_case

import DefaultUseCase
import Resource
import com.example.authstarterkotlin.feature_auth.data.remote.request.RegisterRequest
import com.example.authstarterkotlin.feature_auth.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow

class RegisterUseCase(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(register: RegisterRequest): Flow<Resource<Unit>> {
        return DefaultUseCase(
            onRequest = {
                authRepository.register(registerRequest = register)
            }
        ).execute()
    }
}