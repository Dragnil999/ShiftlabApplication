package com.example.shiftlabapplication.features.start.domain.repository

import kotlinx.coroutines.flow.Flow

interface StartRepository {
    fun getUserName(): Flow<String>
}