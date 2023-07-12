package com.example.authstarterkotlin.core.utils.storage

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.example.authstarterkotlin.core.utils.Constants.EMPTY_JSON_STRING
import com.example.authstarterkotlin.core.utils.Constants.OPERATION_SUCCESS
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import java.lang.reflect.Type

class PersistentStorage<T : Any> constructor(
    private val gson: Gson,
    private val type: Type,
    private val dataStore: DataStore<Preferences>
) : Storage<T> {
    override fun insert(data: T, preferenceKey: Preferences.Key<String>): Flow<Int> {
        println("$data saved as $preferenceKey preference key")
        return flow {
            dataStore.edit {
                it[preferenceKey] = data as String
                emit(OPERATION_SUCCESS)
            }
        }
    }

    override fun insert(data: List<T>, preferenceKey: Preferences.Key<String>): Flow<Int> {
        return flow {
            val cachedDataClone = getAll(preferenceKey = preferenceKey).first().toMutableList()
            cachedDataClone.addAll(data)
            dataStore.edit {
                val jsonString = gson.toJson(cachedDataClone, type)
                it[preferenceKey] = jsonString
                emit(OPERATION_SUCCESS)
            }
        }
    }

    override fun get(where: (T) -> Boolean, preferenceKey: Preferences.Key<String>): Flow<T> {
        return getAll(preferenceKey = preferenceKey).map { cachedData ->
            cachedData.first(where)
        }
    }

    override fun getFirst(preferenceKey: Preferences.Key<String>): Flow<T> {
        return dataStore.data.map { preferences ->
            (preferences[preferenceKey] ?: EMPTY_JSON_STRING) as T
        }
    }

    override fun getAll(preferenceKey: Preferences.Key<String>): Flow<List<T>> {
        val typeToken = object : TypeToken<List<T>>() {}.type

        return dataStore.data.map { preferences ->
            val jsonString = preferences[preferenceKey] ?: EMPTY_JSON_STRING
            val elements: List<T> = gson.fromJson(jsonString, typeToken)
            elements
        }
    }

    override fun clearAll(preferenceKey: Preferences.Key<String>): Flow<Int> {
        return flow {
            dataStore.edit {
                it.remove(preferenceKey)
                emit(OPERATION_SUCCESS)
            }
        }
    }
}
