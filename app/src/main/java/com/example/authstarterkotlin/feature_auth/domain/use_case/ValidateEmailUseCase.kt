package com.example.authstarterkotlin.feature_auth.domain.use_case

import DefaultUseCase
import Resource
import com.example.authstarterkotlin.feature_auth.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow

class ValidateEmailUseCase(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(email:  String): Flow<Resource<Unit>> {
        return DefaultUseCase(
            onRequest = {
                authRepository.validateEmail(email)
            }
        ).execute()
    }
}