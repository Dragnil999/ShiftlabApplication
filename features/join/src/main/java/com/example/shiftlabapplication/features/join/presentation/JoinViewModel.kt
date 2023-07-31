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
        if (userData.name.isEmpty()) {
            _state.value = JoinState.Solved.Name
            return false
        }

        return if (!userData.name.matches(nameRegex)) {
            _state.value = JoinState.Error.Name
            false
        } else {
            _state.value = JoinState.Solved.Name
            _state.value = JoinState.Error.NotAllData
            true
        }
    }

    private fun checkSurname(): Boolean {
        if (userData.surname.isEmpty()) {
            _state.value = JoinState.Solved.Surname
            return false
        }

        return if (!userData.surname.matches(nameRegex)) {
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

        if (userData.birthDate == null) {
            _state.value = JoinState.Solved.BirthDate
            return false
        }

        return if (calendar.get(Calendar.YEAR) > MIN_BIRTH_YEAR) {
            _state.value = JoinState.Error.BirthYear
            false
        } else {
            _state.value = JoinState.Solved.BirthDate
            _state.value = JoinState.Error.NotAllData
            true
        }
    }

    private fun checkPassword(): Boolean {
        if (userData.password.isEmpty()) {
            _state.value = JoinState.Solved.Password
            return false
        }

        return if (!userData.password.matches(passwordRegex)) {
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