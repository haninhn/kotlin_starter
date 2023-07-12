package com.example.authstarterkotlin.core.utils.storage

import androidx.datastore.preferences.core.Preferences
import kotlinx.coroutines.flow.Flow

interface Storage<T> {
    fun insert(data: T, preferenceKey: Preferences.Key<String>): Flow<Int>

    fun insert(data: List<T>, preferenceKey: Preferences.Key<String>): Flow<Int>

    fun get(where: (T) -> Boolean, preferenceKey: Preferences.Key<String>): Flow<T>

    fun getFirst(preferenceKey: Preferences.Key<String>): Flow<T>

    fun getAll(preferenceKey: Preferences.Key<String>): Flow<List<T>>

    fun clearAll(preferenceKey: Preferences.Key<String>): Flow<Int>
}