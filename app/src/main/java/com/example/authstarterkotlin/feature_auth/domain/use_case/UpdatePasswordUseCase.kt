package com.example.authstarterkotlin.feature_auth.domain.use_case

import DefaultUseCase
import Resource
import com.example.authstarterkotlin.feature_auth.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow

class UpdatePasswordUseCase(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(
        email: String,
        code: String,
        password: String
    ): Flow<Resource<Unit>> {
        return DefaultUseCase(
            onRequest = {
                authRepository.updatePassword(
                    email = email,
                    code = code,
                    password = password
                )
            }
        ).execute()
    }
}