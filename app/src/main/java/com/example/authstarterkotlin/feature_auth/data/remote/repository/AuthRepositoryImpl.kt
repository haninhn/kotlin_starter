package com.example.authstarterkotlin.feature_auth.data.remote.repository

import AccessTokenPreferenceKey
import OfflineException
import android.content.SharedPreferences
import android.util.Log
import com.example.authstarterkotlin.core.network.ConnectivityObserver
import com.example.authstarterkotlin.core.utils.Constants
import com.example.authstarterkotlin.core.utils.storage.Storage
import com.example.authstarterkotlin.feature_auth.data.remote.AuthRemoteDataSource
import com.example.authstarterkotlin.feature_auth.data.remote.request.LoginRequest
import com.example.authstarterkotlin.feature_auth.data.remote.request.RegisterRequest
import com.example.authstarterkotlin.feature_auth.domain.repository.AuthRepository

class AuthRepositoryImpl(
    private val authRemoteDataSource: AuthRemoteDataSource,
    private val sharedPreferences: SharedPreferences,
    private val storage: Storage<String>
) : AuthRepository {
    override suspend fun login(email: String, passwod: String) {
        if (Constants.internetStatus == ConnectivityObserver.Status.Available) {
            val remoteResponse = authRemoteDataSource.login(loginRequest = LoginRequest(email,passwod))
            Log.e("LoginResponse", "$remoteResponse")

            if (remoteResponse.statusCode == 200) {

                //save access token in sharedPreferences
                 with(sharedPreferences.edit()) {
                    putString(AccessTokenPreferenceKey.name, remoteResponse.data?.accessToken)
                    apply()
                }
                //insert access token in storage
                storage
                    .insert(
                        data = remoteResponse.data?.accessToken ?: "",
                        preferenceKey = AccessTokenPreferenceKey
                    )
        }
    } else throw OfflineException()
    }

    override suspend fun register(registerRequest: RegisterRequest) {
        if (Constants.internetStatus == ConnectivityObserver.Status.Available) {
            val remoteResponse = authRemoteDataSource.register(registerRequest = registerRequest)
              Log.e("Response", "$remoteResponse")
          } else throw OfflineException()
    }


}