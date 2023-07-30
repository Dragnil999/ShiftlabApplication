package com.example.shiftlabapplication.features.join.domain.usecase

import com.example.shiftlabapplication.features.join.domain.repository.JoinRepository

class SendNameUseCase(private val repository: JoinRepository) {
    suspend operator fun invoke(name: String) = repository.saveUserName(name)
}