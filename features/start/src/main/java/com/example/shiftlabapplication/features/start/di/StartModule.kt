package com.example.shiftlabapplication.features.start.di

import com.example.shiftlabapplication.features.start.data.datasource.StartDataSource
import com.example.shiftlabapplication.features.start.data.datasource.StartDataSourceImpl
import com.example.shiftlabapplication.features.start.data.repository.StartRepositoryImpl
import com.example.shiftlabapplication.features.start.domain.repository.StartRepository
import com.example.shiftlabapplication.features.start.domain.usecase.GetUserNameUseCase
import com.example.shiftlabapplication.features.start.presentation.StartViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val startModule = module {
    factory<StartDataSource> {
        StartDataSourceImpl(
            dataStore = get(),
            nameKey = get(),
        )
    }

    factory<StartRepository> { StartRepositoryImpl(dataSource = get()) }
    factory { GetUserNameUseCase(repository = get()) }

    viewModel {
        StartViewModel(
            navigation = get(),
            getUserNameUseCase = get(),
        )
    }
}