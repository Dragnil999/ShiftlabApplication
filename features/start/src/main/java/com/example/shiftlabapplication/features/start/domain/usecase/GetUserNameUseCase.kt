package com.example.shiftlabapplication.features.start.domain.usecase

import com.example.shiftlabapplication.features.start.domain.repository.StartRepository
import kotlinx.coroutines.flow.Flow

class GetUserNameUseCase(private val repository: StartRepository) {
    operator fun invoke(): Flow<String> = repository.getUserName()
}