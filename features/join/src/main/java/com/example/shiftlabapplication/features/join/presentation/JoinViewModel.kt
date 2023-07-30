package com.example.shiftlabapplication.features.join.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shiftlabapplication.features.join.navigation.JoinNavigation

class JoinViewModel(
    private val navigation: JoinNavigation,
) : ViewModel() {
    private val _state: MutableLiveData<JoinState> = MutableLiveData(JoinState.Initial)
    val state: LiveData<JoinState> = _state

    private val passwordRegex = Regex("(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9@#\$%]).{8,}")

    var name: String = ""
    var surname: String = ""
    var birthDate: String = ""
    var password: String = ""
    var isPasswordChecked = false

    fun checkName() {
        if (name.length <= 2) {
            _state.value = JoinState.Error.Name
        } else {
            _state.value = JoinState.Error.NotAllData
        }
    }

    fun checkSurname() {
        if (surname.length <= 4) {
            _state.value = JoinState.Error.Surname
        } else {
            _state.value = JoinState.Error.NotAllData
        }
    }


    fun checkPassword() {
        if (!password.matches(passwordRegex)) {
            _state.value = JoinState.Error.Password
        } else {
            Log.d("JoinViewModel", "NotAllData has been reached")
            _state.value = JoinState.Error.NotAllData
        }
    }

    fun checkRepeatPassword(repeatPassword: String) {
        if (repeatPassword != password) {
            isPasswordChecked = true
            _state.value = JoinState.Error.RepeatPassword
        } else {
            _state.value = JoinState.Error.NotAllData
        }
    }

    private fun checkAllDataSet(): Boolean {
        return !(name.isEmpty() ||
                surname.isEmpty() ||
                birthDate.isEmpty() ||
                password.isEmpty() ||
                !isPasswordChecked)
    }

    fun checkSummary() {
        if (_state.value is JoinState.Error.NotAllData && checkAllDataSet()) {
            _state.value = JoinState.NoError
        }
    }

    fun goToStartScreen() {
        navigation.goToStartScreen()
    }
}