package com.example.authstarterkotlin.feature_auth.domain.use_case

import DefaultUseCase
import Resource
import com.example.authstarterkotlin.feature_auth.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow

class ValidateCodeUseCase(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(
        code: String,
        email: String
    ): Flow<Resource<Unit>> {
        return DefaultUseCase(
            onRequest = {
                authRepository.validateCode(
                    code,
                    email
                )
            }
        ).execute()
    }
}