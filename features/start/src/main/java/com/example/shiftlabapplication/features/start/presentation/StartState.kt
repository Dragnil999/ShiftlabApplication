package com.example.shiftlabapplication.features.start.presentation

sealed class StartState {
    object Loading : StartState()
    object Hello: StartState()
}
