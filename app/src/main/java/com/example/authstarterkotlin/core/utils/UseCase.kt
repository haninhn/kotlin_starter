package com.example.authstarterkotlin.core.utils

import kotlinx.coroutines.flow.Flow

interface UseCase<T> {
    suspend fun execute(): Flow<Resource<T>>
}