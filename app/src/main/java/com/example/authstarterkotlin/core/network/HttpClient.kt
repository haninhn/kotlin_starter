package com.example.authstarterkotlin.core.network

import AccessTokenPreferenceKey
import android.content.SharedPreferences
import com.example.authstarterkotlin.core.utils.storage.Storage
import io.ktor.client.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json

@OptIn(ExperimentalSerializationApi::class)
fun createHttpClient(
    enableLogging: Boolean,
    sharedPreferences: SharedPreferences,
    storage: Storage<String>
): HttpClient {
    return HttpClient {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                explicitNulls = false
            })
        }

        HttpResponseValidator {
            validateResponse { response ->
                val statusCode = response.status.value
                println("HTTP status: $statusCode")
                println("response: $response")
                when (statusCode) {
                    !in 200..299 -> {
                        throw ServerResponseException(
                            response,
                            "ServerResponseException"
                        )
                    }
                }
            }

            handleResponseExceptionWithRequest { cause: Throwable, _ ->
                throw cause
            }
        }

        install(DefaultRequest) {
            if (!headers.contains("No-Authentication")) {
                header("Authorization", sharedPreferences.getString(AccessTokenPreferenceKey.name, null) ?: "")
            }
        }

        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.ALL
        }
    }
}