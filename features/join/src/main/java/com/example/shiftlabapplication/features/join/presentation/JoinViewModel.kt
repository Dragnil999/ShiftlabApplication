package com.example.shiftlabapplication.features.join.presentation

import android.icu.util.Calendar
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

    private val nameRegex = Regex("(?=[A-Z]).{3,}|(?=[А-Я]).{3,}")
    private val passwordRegex = Regex("(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9@#\$%]).{8,}")

    var userData: UserDataEntity = UserDataEntity()
    private var isPasswordChecked = false

    private fun checkName(): Boolean {
        return if (!userData.name.matches(nameRegex) && userData.name.isNotEmpty()) {
            _state.value = JoinState.Error.Name
            false
        } else {
            _state.value = JoinState.Solved.Name
            _state.value = JoinState.Error.NotAllData
            true
        }
    }

    private fun checkSurname(): Boolean {
        return if (!userData.surname.matches(nameRegex) && userData.surname.isNotEmpty()) {
            _state.value = JoinState.Error.Surname
            false
        } else {
            _state.value = JoinState.Solved.Surname
            _state.value = JoinState.Error.NotAllData
            true
        }
    }

    private fun checkBirthDate(): Boolean {
        val calendar: Calendar = Calendar.getInstance()
        calendar.timeInMillis = userData.birthDate?.time ?: 0

        return if (calendar.get(Calendar.YEAR) > MIN_BIRTH_YEAR && userData.birthDate != null) {
            _state.value = JoinState.Error.BirthYear
            false
        } else {
            _state.value = JoinState.Solved.BirthDate
            _state.value = JoinState.Error.NotAllData
            true
        }
    }

    private fun checkPassword(): Boolean {
        return if (!userData.password.matches(passwordRegex) && userData.password.isNotEmpty()) {
            _state.value = JoinState.Error.Password
            false
        } else {
            _state.value = JoinState.Solved.Password
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
            isPasswordChecked = repeatPassword.isNotEmpty()
            _state.value = JoinState.Solved.RepeatPassword
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
        checkBirthDate()
        checkPassword()
        if (_state.value is JoinState.Error.NotAllData && checkAllDataSet()) {
            _state.value = JoinState.NoError
        }
    }

    fun sendUserData() {
        viewModelScope.launch {
            sendNameUseCase(userData)
            goToStartScreen()
        }
    }

    private fun goToStartScreen() {
        navigation.goToStartScreen()
    }
}