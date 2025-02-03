package ru.sicampus.bootcamp2025.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RegistrationViewModel: ViewModel() {
    private val _state = MutableStateFlow<State>(State.Input)
    val state = _state.asStateFlow()

    sealed interface State{
        object Loading: State
        object Success: State
        object Input: State
        data class Error (val text: String): State
    }

    fun signUp(email: String, password: String) {
        if (email.isBlank() || password.isBlank()) {
            _state.update { State.Error("Поля не должны быть пустыми") }
            return
        }

        _state.update { State.Loading }
        viewModelScope.launch {
            kotlinx.coroutines.delay(2000)
            if (email == "test@example.com" && password == "password") {
                _state.update { State.Success }
            } else {
                _state.update { State.Error("Неверные данные") }
            }
        }
    }

    fun resetState() {
        _state.update { State.Input }
    }
}