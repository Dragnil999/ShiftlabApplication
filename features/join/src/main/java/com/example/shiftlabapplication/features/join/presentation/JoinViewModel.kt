package com.example.shiftlabapplication.features.join.presentation

import android.icu.util.Calendar
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shiftlabapplication.features.join.domain.entity.UserDataEntity
import com.example.shiftlabapplication.features.join.domain.usecase.SendNameUseCase
import com.example.shiftlabapplication.features.join.navigation.JoinNavigation
import kotlinx.coroutines.launch

class JoinViewModel(
    private val navigation: JoinNavigation,
    private val sendNameUseCase: SendNameUseCase,
) : ViewModel() {
    companion object {
        private const val MIN_BIRTH_YEAR = 2020
    }

    private val _state: MutableLiveData<JoinState> = MutableLiveData(JoinState.Initial)
    val state: LiveData<JoinState> = _state

    private val passwordRegex = Regex("(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9@#\$%]).{8,}")

    var userData: UserDataEntity = UserDataEntity()
    var isPasswordChecked = false

    private fun checkName(): Boolean {
        return if (userData.name.length <= 2 && userData.name.isNotEmpty()) {
            _state.value = JoinState.Error.Name
            false
        } else {
            _state.value = JoinState.Error.NotAllData
            true
        }
    }

    private fun checkSurname(): Boolean {
        return if (userData.surname.length <= 4 && userData.surname.isNotEmpty()) {
            _state.value = JoinState.Error.Surname
            false
        } else {
            _state.value = JoinState.Error.NotAllData
            true
        }
    }

    private fun checkBirthDate(): Boolean {
        val calendar: Calendar = Calendar.getInstance()
        calendar.timeInMillis = userData.birthDate.time

        return if (calendar.get(Calendar.YEAR) > MIN_BIRTH_YEAR) {
            _state.value = JoinState.Error.BirthYear
            false
        } else {
            _state.value = JoinState.Error.NotAllData
            true
        }
    }

    private fun checkPassword(): Boolean {
        return if (!userData.password.matches(passwordRegex) && userData.password.isNotEmpty()) {
            _state.value = JoinState.Error.Password
            false
        } else {
            _state.value = JoinState.Error.NotAllData
            true
        }
    }

    fun checkRepeatPassword(repeatPassword: String): Boolean {
        return if (repeatPassword != userData.password && repeatPassword.isNotEmpty()) {
            isPasswordChecked = false
            _state.value = JoinState.Error.RepeatPassword
            false
        } else {
            isPasswordChecked = true
            _state.value = JoinState.Error.NotAllData
            true
        }
    }

    private fun checkAllDataSet(): Boolean {
        return checkName() && checkSurname() && checkBirthDate() && checkPassword() && isPasswordChecked
    }

    fun checkSummary() {
        checkName()
        checkSurname()
        checkPassword()
        Log.d("JoinViewModel", "state is ${_state.value}")
        Log.d("JoinViewModel", "checkAllDataSet() = ${checkAllDataSet()}")
        if (_state.value is JoinState.Error.NotAllData && checkAllDataSet()) {
            _state.value = JoinState.NoError
        }
    }

    fun sendUserName() {
        viewModelScope.launch {
            sendNameUseCase(userData.name)
            goToStartScreen()
        }
    }

    private fun goToStartScreen() {
        navigation.goToStartScreen()
    }
}