package com.example.authstarterkotlin.feature_auth.domain.use_case
import DefaultUseCase
import Resource
import com.example.authstarterkotlin.feature_auth.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow

class RefreshTokenUseCase(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(): Flow<Resource<String?>> {
        return DefaultUseCase(
            onRequest = {
                authRepository.refreshToken()
            }
        ).execute()
    }
}