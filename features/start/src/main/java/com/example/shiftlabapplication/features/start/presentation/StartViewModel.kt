package com.example.shiftlabapplication.features.start.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shiftlabapplication.features.start.domain.usecase.GetUserNameUseCase
import com.example.shiftlabapplication.features.start.navigation.StartNavigation
import com.example.shiftlabapplication.features.start.ui.HelloDialog
import com.example.shiftlabapplication.features.start.ui.StartFragment
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.launch

class StartViewModel(
    private val navigation: StartNavigation,
    private val getUserNameUseCase: GetUserNameUseCase,
) : ViewModel() {
    private val _state: MutableLiveData<StartState> = MutableLiveData(StartState.Loading)
    val state: LiveData<StartState> = _state

    lateinit var userName: String

    fun getUserName() {
        viewModelScope.launch {
            userName = getUserNameUseCase().first()
            if (userName == "Not registered") {
                goToJoinScreen()
            } else {
                _state.value = StartState.Hello
            }
        }
    }

    private fun goToJoinScreen() {
        navigation.goToJoinScreen()
    }
}