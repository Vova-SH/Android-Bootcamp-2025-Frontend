package ru.sicampus.bootcamp2025.ui.entry

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import ru.sicampus.bootcamp2025.Const.TOKEN_KEY
import ru.sicampus.bootcamp2025.data.UserRepositoryImpl
import ru.sicampus.bootcamp2025.data.sources.locale.CredentialsLocalDataSource
import ru.sicampus.bootcamp2025.data.sources.locale.UserLocalDataSource
import ru.sicampus.bootcamp2025.data.sources.network.UserNetworkDataSource
import ru.sicampus.bootcamp2025.domain.usecases.AuthorizeUseCase
import ru.sicampus.bootcamp2025.domain.usecases.GetCurrentUserCredentialsUseCase

class AuthViewModel(
    private val authorizeUseCase: AuthorizeUseCase,
    private val getCurrentUserCredentialsUseCase: GetCurrentUserCredentialsUseCase,
    private val application: Application
) : AndroidViewModel(application) {

    private val _state = MutableStateFlow<State>(State.Loading)
    private val _action = Channel<Action>(
        capacity = Channel.BUFFERED,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    val action = _action.receiveAsFlow()

    init {
        viewModelScope.launch {
            updateState()
        }
    }

    private fun updateState() {
        viewModelScope.launch {
            _state.emit(State.Loading)
            val currentUser = getCurrentUserCredentialsUseCase.invoke()
            if (currentUser == null) goToSignUp()
            else {
                authorizeUseCase.invoke(currentUser).fold(
                    onSuccess = {
                        _state.emit(State.Process)
                        openApp()
                    },
                    onFailure = { error ->
                        _state.emit(State.Error(error.message.toString()))
                        goToLogin()
                    }
                )
            }
        }
    }

    private fun openApp() {
        viewModelScope.launch {
            _action.send(Action.OpenApp)
        }
    }

    private fun goToLogin() {
        viewModelScope.launch {
            _action.send(Action.GoToLogin)
        }
    }

    private fun goToSignUp() {
        viewModelScope.launch {
            _action.send(Action.GotToSignUp)
        }
    }

    sealed interface State {
        data object Loading : State
        data object Process : State
        data class Error(val errorMessage: String) : State
    }

    sealed interface Action {
        data object OpenApp : Action
        data object GoToLogin : Action
        data object GotToSignUp : Action
    }

    companion object {
        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
                val application =
                    extras[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY]!!
                val repository = UserRepositoryImpl(
                    userLocalDataSource = UserLocalDataSource,
                    credentialsLocalDataSource = CredentialsLocalDataSource.getInstance(
                        application.getSharedPreferences(
                            TOKEN_KEY,
                            Context.MODE_PRIVATE
                        )
                    ),
                    userNetworkDataSource = UserNetworkDataSource
                )

                return AuthViewModel(
                    authorizeUseCase = AuthorizeUseCase(repository),
                    getCurrentUserCredentialsUseCase = GetCurrentUserCredentialsUseCase(repository),
                    application = application
                    ) as T
            }
        }
    }
}