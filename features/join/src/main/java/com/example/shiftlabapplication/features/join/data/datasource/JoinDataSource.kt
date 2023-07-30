package com.example.shiftlabapplication.features.join.data.datasource

interface JoinDataSource {
    suspend fun saveUserName(name: String)
}