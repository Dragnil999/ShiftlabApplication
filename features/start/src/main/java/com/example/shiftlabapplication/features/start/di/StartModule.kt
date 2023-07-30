package com.example.shiftlabapplication.features.start.di

import com.example.shiftlabapplication.features.start.data.repository.StartRepositoryImpl
import com.example.shiftlabapplication.features.start.domain.repository.StartRepository
import com.example.shiftlabapplication.features.start.domain.usecase.GetUserNameUseCase
import com.example.shiftlabapplication.features.start.presentation.StartViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val startModule = module {
    factory<StartRepository> {
        StartRepositoryImpl(
            dataStore = get(),
            nameKey = get(),
        )
    }

    factory { GetUserNameUseCase(repository = get()) }

    viewModel {
        StartViewModel(
            navigation = get(),
            getUserNameUseCase = get(),
        )
    }
}