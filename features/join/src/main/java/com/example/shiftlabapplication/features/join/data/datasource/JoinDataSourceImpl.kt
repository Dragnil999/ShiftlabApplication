package com.example.shiftlabapplication.features.join.data.datasource

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit

class JoinDataSourceImpl(
    private val dataStore: DataStore<Preferences>,
    private val nameKey: Preferences.Key<String>,
) : JoinDataSource {
    override suspend fun saveUserName(name: String) {
        dataStore.edit { preferences ->
            preferences[nameKey] = name
        }
    }
}