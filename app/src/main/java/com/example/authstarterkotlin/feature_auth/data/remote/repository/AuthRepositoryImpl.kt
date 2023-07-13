package com.example.authstarterkotlin.feature_auth.data.remote.repository

import AccessTokenPreferenceKey
import OfflineException
import RefreshTokenPreferenceKey
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
    override suspend fun login(email: String, password: String) {
        if (Constants.internetStatus == ConnectivityObserver.Status.Available) {
            val remoteResponse = authRemoteDataSource.login(loginRequest = LoginRequest(email,password))
            Log.e("LoginResponse", "$remoteResponse")

            if (remoteResponse.statusCode == 200) {
                //Save The token in sharedPreferences
                 with(sharedPreferences.edit()) {
                    putString(AccessTokenPreferenceKey.name, remoteResponse.data?.accessToken)
                    apply()
                }

                with(sharedPreferences.edit()) {
                    putString(RefreshTokenPreferenceKey.name, remoteResponse.data?.refreshToken)
                    apply()
                }
        }
    } else throw OfflineException()
  }

    override suspend fun register(registerRequest: RegisterRequest) {
        if (Constants.internetStatus == ConnectivityObserver.Status.Available) {
            val remoteResponse = authRemoteDataSource.register(registerRequest = registerRequest)
              Log.e("registerResponse", "$remoteResponse")
          } else throw OfflineException()
    }

    override suspend fun validateEmail(email: String) {
        if (Constants.internetStatus == ConnectivityObserver.Status.Available) {
            val remoteResponse = authRemoteDataSource.validateEmail(validateEmailRequest = email)
            Log.e("validateEmailResponse", "$remoteResponse")
        } else throw OfflineException()
    }

    override suspend fun validateCode(code: String, email: String) {
        if (Constants.internetStatus == ConnectivityObserver.Status.Available) {
            val remoteResponse = authRemoteDataSource.validateCode(validateCodeRequest = code, email = email)
            Log.e("validateCodeResponse", "$remoteResponse")
        } else
            throw OfflineException()

    }

    override suspend fun updatePassword(email: String, code: String, password: String) {
        if (Constants.internetStatus == ConnectivityObserver.Status.Available) {
            val remoteResponse = authRemoteDataSource.updatePassword(email = email, code= code, password= password)
            Log.e("updatePassword", "$remoteResponse")
        } else throw OfflineException()
    }

        override suspend fun refreshToken(): String? {
            if (Constants.internetStatus == ConnectivityObserver.Status.Available) {
                val refreshToken = sharedPreferences.getString(RefreshTokenPreferenceKey.name, "") ?: ""
                if (refreshToken.isBlank()) return null

                val remoteResponse = authRemoteDataSource.refreshToken(refreshTokenRequest = refreshToken)
                Log.e("RefreshTokenResponse", "$remoteResponse")

                if (remoteResponse.statusCode == 200) {
                    with(sharedPreferences.edit()) {
                        putString(AccessTokenPreferenceKey.name, remoteResponse.data?.accessToken?: "")
                        apply()
                    }

                    with(sharedPreferences.edit()) {
                        putString(RefreshTokenPreferenceKey.name, remoteResponse.data?.newRefreshToken?: "")
                        apply()
                    }

                }
            }
            return null

        }


}