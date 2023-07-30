package com.example.shiftlabapplication.features.join.di

import com.example.shiftlabapplication.features.join.presentation.JoinViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val joinModule = module {
    viewModel {
        JoinViewModel(
            navigation = get(),
        )
    }
}