package com.example.shiftlabapplication.features.start.di

import com.example.shiftlabapplication.features.start.presentation.StartViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val startModule = module {
    viewModel {
        StartViewModel(
            navigation = get(),
        )
    }
}