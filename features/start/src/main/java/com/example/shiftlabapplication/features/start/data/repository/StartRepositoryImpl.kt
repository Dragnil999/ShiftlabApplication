package com.example.shiftlabapplication.features.start.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.example.shiftlabapplication.features.start.data.datasource.StartDataSource
import com.example.shiftlabapplication.features.start.domain.repository.StartRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class StartRepositoryImpl(private val dataSource: StartDataSource) : StartRepository {
    override fun getUserName(): Flow<String> = dataSource.getUserName()
}