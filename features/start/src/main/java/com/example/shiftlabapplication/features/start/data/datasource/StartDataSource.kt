package com.example.shiftlabapplication.features.start.data.datasource

import kotlinx.coroutines.flow.Flow

interface StartDataSource {
    fun getUserName(): Flow<String>
}