package ru.sicampus.bootcamp2025.ui.entry.login

import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {

    sealed interface State {
        data object Loading: State
    }
}