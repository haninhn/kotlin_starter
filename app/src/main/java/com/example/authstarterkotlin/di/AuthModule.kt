package com.example.authstarterkotlin.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.example.authstarterkotlin.core.utils.storage.PersistentStorage
import com.example.authstarterkotlin.core.utils.storage.Storage
import com.example.authstarterkotlin.feature_auth.data.remote.AuthApi
import com.example.authstarterkotlin.feature_auth.data.remote.AuthRemoteDataSource
import com.example.authstarterkotlin.feature_auth.data.remote.repository.AuthRepositoryImpl
import com.example.authstarterkotlin.feature_auth.domain.repository.AuthRepository
import com.example.authstarterkotlin.feature_auth.domain.use_case.LoginUseCase
import com.example.authstarterkotlin.feature_auth.domain.use_case.RegisterUseCase
import com.example.authstarterkotlin.feature_auth.presentation.login.LoginViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "com.example.AuthStarterKotlin.shared.preferences")

val AuthModule = module {
    single { AuthApi(httpClient = get()) }
    single { AuthRemoteDataSource(authApi = get()) }

    single<AuthRepository> {
        AuthRepositoryImpl(
            authRemoteDataSource = get(),
            storage = get(),
            sharedPreferences = get()
        )
    }
    single { LoginUseCase(authRepository = get()) }
    single { RegisterUseCase(authRepository = get()) }


    single<Storage<String>> {
        PersistentStorage(
            gson = get(),
            type = object : TypeToken<List<String>>() {}.type,
            dataStore = androidContext().dataStore
        )
    }

    single { Gson() }

    viewModel {
        LoginViewModel(loginUseCase = get())
    }


}