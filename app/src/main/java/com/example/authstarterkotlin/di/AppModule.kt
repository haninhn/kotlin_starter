package com.example.authstarterkotlin.di

import android.content.Context
import android.content.SharedPreferences
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

fun initKoin(enableNetworkLogs: Boolean = false, appDeclaration: KoinAppDeclaration = {}) =
    startKoin {
        appDeclaration()
        modules(
            listOf(
                module {
                    single<SharedPreferences> {
                        get<Context>().getSharedPreferences(
                            "prefs",
                            Context.MODE_PRIVATE
                        )
                    }
                },
                networkModule(enableNetworkLogs),
                AuthModule
            )
        )
    }
