package com.example.shiftlabapplication.features.join.presentation

sealed class JoinState {
    object Initial : JoinState()
    sealed class Error : JoinState() {
        object NotAllData : Error()
        object Name : Error()
        object Surname : Error()
        object Password : Error()
        object RepeatPassword : Error()
    }
    object NoError : JoinState()
}
