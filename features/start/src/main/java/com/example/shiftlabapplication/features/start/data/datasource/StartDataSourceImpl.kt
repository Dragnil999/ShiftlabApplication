package com.example.shiftlabapplication.features.start.data.datasource

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class StartDataSourceImpl(
    private val dataStore: DataStore<Preferences>,
    private val nameKey: Preferences.Key<String>,
) : StartDataSource {
    override fun getUserName(): Flow<String> =
        dataStore.data.map { preferences ->
            preferences[nameKey] ?: "Not registered"
        }
}