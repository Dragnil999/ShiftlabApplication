package com.example.shiftlabapplication.features.join.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.example.shiftlabapplication.features.join.domain.repository.JoinRepository

class JoinRepositoryImpl(
    private val dataStore: DataStore<Preferences>,
    private val nameKey: Preferences.Key<String>,
) : JoinRepository {
    override suspend fun saveUserName(name: String) {
        dataStore.edit { preferences ->
            preferences[nameKey] = name
        }
    }
}