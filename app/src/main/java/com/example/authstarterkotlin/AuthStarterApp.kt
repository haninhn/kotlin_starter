package com.example.authstarterkotlin

import android.app.Application
import com.example.authstarterkotlin.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger

class AuthStarterApp : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(applicationContext)
            androidLogger()
        }
    }
}