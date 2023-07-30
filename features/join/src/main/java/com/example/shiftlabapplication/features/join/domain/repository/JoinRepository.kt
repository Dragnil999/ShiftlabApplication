package com.example.shiftlabapplication.features.join.domain.repository

interface JoinRepository {
    suspend fun saveUserName(name: String)
}