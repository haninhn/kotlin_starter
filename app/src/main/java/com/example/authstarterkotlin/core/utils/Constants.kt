package com.example.authstarterkotlin.core.utils

import com.example.authstarterkotlin.core.network.ConnectivityObserver

object Constants {
    const val EMPTY_JSON_STRING = ""
    const val OPERATION_SUCCESS = 1
    lateinit var internetStatus: ConnectivityObserver.Status

}