package com.example.shiftlabapplication.features.join.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.example.shiftlabapplication.features.join.data.datasource.JoinDataSource
import com.example.shiftlabapplication.features.join.domain.entity.UserDataEntity
import com.example.shiftlabapplication.features.join.domain.repository.JoinRepository

class JoinRepositoryImpl(private val dataSource: JoinDataSource) : JoinRepository {
    override suspend fun saveUserName(user: UserDataEntity) =  dataSource.saveUserName(user.name)
}