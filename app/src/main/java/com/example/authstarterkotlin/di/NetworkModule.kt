package com.example.authstarterkotlin.di

import com.example.authstarterkotlin.core.network.createHttpClient
import org.koin.core.module.Module
import org.koin.dsl.module


val networkModule: (enableLogging: Boolean) -> Module
    get() = { enableLogging ->
        module {
            single { createHttpClient(enableLogging = enableLogging, sharedPreferences = get(), storage = get()) }
        }
    }