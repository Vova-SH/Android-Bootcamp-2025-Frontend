package ru.sicampus.bootcamp2025.ui.entry

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import ru.sicampus.bootcamp2025.domain.entities.UserEntity

class AuthViewModel : ViewModel() {

    private val _state = MutableStateFlow<State>(State.Loading)
    val state = _state.asStateFlow()

    private val _action = Channel<Action>(
        capacity = Channel.BUFFERED,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    val action = _action.receiveAsFlow()

    private val isNewUser: Boolean? = null

    init {
        viewModelScope.launch {
            updateState()
        }
    }

    private suspend fun updateState() {
        _state.emit(
            State.Loading
        )
        when (isNewUser) {
            true -> TODO()
            false -> TODO()
            null -> TODO()
        }
    }

    sealed interface State {
        data object Loading : State
        data class Process(
            val userEntity: UserEntity
        ) : State

        data class Error(
            val errorMessage: String
        )
    }

    sealed interface Action {
        data object OpenApp : Action
    }

    fun isReady(): Boolean {

    }

    fun openApp() {
        viewModelScope.launch {
            _action.send(Action.OpenApp)
        }
    }
}