package com.example.shiftlabapplication.features.join.presentation

sealed class JoinState {
    object Initial : JoinState()
    sealed class Error : JoinState() {
        object NotAllData : Error()
        object Name : Error()
        object Surname : Error()
        object BirthYear: Error()
        object Password : Error()
        object RepeatPassword : Error()
    }
    sealed class Solved : JoinState() {
        object Name : Solved()
        object Surname : Solved()
        object BirthDate : Solved()
        object Password : Solved()
        object RepeatPassword : Solved()
    }
    object NoError : JoinState()
}
