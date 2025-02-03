package ru.sicampus.bootcamp2025.ui.mainscreen.centerinfo

import androidx.lifecycle.ViewModel

class CenterInfoViewModel : ViewModel() {

    sealed interface State {
        data object Loading : State
        data object Error : State
        data object Show : State
    }
}