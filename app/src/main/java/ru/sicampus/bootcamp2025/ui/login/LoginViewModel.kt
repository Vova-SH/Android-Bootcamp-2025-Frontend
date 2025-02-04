package ru.sicampus.bootcamp2025.ui.login

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class LoginViewModel : ViewModel(){
    fun checkUserAuth(login: String): Boolean {
        TODO()
    }

    fun saveUserLogin(login: String, sharedPreferences: SharedPreferences) {
        TODO()
    }

    private val _state = MutableStateFlow(true)
    val state = _state.asStateFlow()
}
