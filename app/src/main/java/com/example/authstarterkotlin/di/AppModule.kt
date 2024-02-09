package com.example.authstarterkotlin.di

import android.content.Context
import android.content.SharedPreferences
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

fun initKoin( appDeclaration: KoinAppDeclaration = {}) =
    startKoin {
        appDeclaration()
        modules(
            listOf(
                databaseModule,
                studentModule

            )
        )
    }
