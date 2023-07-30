package com.example.shiftlabapplication.features.join.di

import com.example.shiftlabapplication.features.join.data.repository.JoinRepositoryImpl
import com.example.shiftlabapplication.features.join.domain.repository.JoinRepository
import com.example.shiftlabapplication.features.join.domain.usecase.SendNameUseCase
import com.example.shiftlabapplication.features.join.presentation.JoinViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val joinModule = module {
    factory<JoinRepository> {
        JoinRepositoryImpl(
            dataStore = get(),
            nameKey = get(),
        )
    }
    factory { SendNameUseCase(repository = get()) }

    viewModel {
        JoinViewModel(
            navigation = get(),
            sendNameUseCase = get(),
        )
    }
}