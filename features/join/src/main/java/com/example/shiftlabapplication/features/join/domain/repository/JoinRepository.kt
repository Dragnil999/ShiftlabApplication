package com.example.shiftlabapplication.features.join.domain.repository

import com.example.shiftlabapplication.features.join.domain.entity.UserDataEntity

interface JoinRepository {
    suspend fun saveUserName(user: UserDataEntity)
}