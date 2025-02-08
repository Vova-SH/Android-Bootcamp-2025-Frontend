package ru.sicampus.bootcamp2025.ui.auth

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import ru.sicampus.bootcamp2025.domain.auth.IsUserExistUseCase
import ru.sicampus.bootcamp2025.domain.auth.LoginUseCase
import ru.sicampus.bootcamp2025.domain.auth.RegisterUserUseCase
import ru.sicampus.bootcamp2025.R
import ru.sicampus.bootcamp2025.data.auth.AuthNetworkDataSource
import ru.sicampus.bootcamp2025.data.auth.AuthRepoImpl
import ru.sicampus.bootcamp2025.data.auth.AuthStorageDataSource
import ru.sicampus.bootcamp2025.domain.auth.AutoLoginUseCase
import ru.sicampus.bootcamp2025.utils.toReadableMessage
import kotlin.reflect.KClass


class AuthViewModel(
    application: Application,
    private val isUserExistUseCase: IsUserExistUseCase,
    private val loginUseCase: LoginUseCase,
    private val registerUserUseCase: RegisterUserUseCase,
    private val autoLoginUseCase: AutoLoginUseCase,
    private val authStorageDataSource: AuthStorageDataSource
) : AndroidViewModel(application = application) {
    private val _state = MutableStateFlow<State>(getStateShow())
    val state = _state.asStateFlow()

    private val _action = Channel<Action>(
        capacity = Channel.BUFFERED,
        onBufferOverflow = BufferOverflow.DROP_OLDEST,
    )
    val action = _action.receiveAsFlow()

    private var isNewUser: Boolean? = null

    fun changeLogin() {
        viewModelScope.launch {
            isNewUser = null
            updateState()
        }
    }

    fun clickNext(
        login: String,
        password: String,
        name: String,
        email: String
    ) {
        viewModelScope.launch {
            _state.emit(State.Loading)
            when (isNewUser) {
                true -> {
                    registerUserUseCase(login, password, name, email).fold(
                        onSuccess = { openList() },
                        onFailure = { error ->
                            updateState(error)
                        }
                    )
                }
                false -> {
                    loginUseCase(login, password).fold(
                        onSuccess = { openList() },
                        onFailure = { error ->
                            updateState(error)
                        }
                    )
                }
                null -> {
                    isUserExistUseCase(login).fold(
                        onSuccess = { isExist ->
                            isNewUser = isExist
                            updateState()
                        },
                        onFailure = { error ->
                            updateState(error)
                        }
                    )
                }
            }
        }
    }

    private fun openList() {
        viewModelScope.launch { _action.send(Action.GoToList) }
    }

    private suspend fun updateState(error: Throwable? = null) {
        _state.emit(getStateShow(error))
    }

    private fun getStateShow(error: Throwable? = null): State.Show {
        return State.Show(
            titleText = when (isNewUser) {
                true -> getApplication<Application>().getString(R.string.auth_login_button)
                false -> getApplication<Application>().getString(R.string.button_register)
                null -> getApplication<Application>().getString(R.string.common_welcome)
            },
            showPassword = isNewUser != null,
            buttonText = when (isNewUser) {
                true -> getApplication<Application>().getString(R.string.auth_login_button)
                false -> getApplication<Application>().getString(R.string.button_register)
                null -> getApplication<Application>().getString(R.string.auth_next_button)
            },
            errorText = error?.toReadableMessage(getApplication()),
        )
    }

    fun checkAutoLogin() {
        viewModelScope.launch {
            if (authStorageDataSource.isLoggedIn()) {
                _state.emit(State.Loading)
                autoLoginUseCase().fold(
                    onSuccess = { openList() },
                    onFailure = { error ->
                        authStorageDataSource.clear()
                        updateState(error)
                    }
                )
            }
        }
    }


    sealed interface State {
        data object Loading : State
        data class Show(
            val titleText: String,
            val showPassword: Boolean,
            val buttonText: String,
            val errorText: String?,
        ) : State
    }

    sealed interface Action {
        data object GoToList : Action
    }

    companion object {
        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: KClass<T>, extras: CreationExtras): T {
                val application = extras[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY]!!

                val authNetworkDataSource = AuthNetworkDataSource
                val authStorageDataSource = AuthStorageDataSource()

                val authRepoImpl = AuthRepoImpl(
                    authNetworkDataSource = authNetworkDataSource,
                    authStorageDataSource = authStorageDataSource
                )

                return AuthViewModel(
                    application = application,
                    isUserExistUseCase = IsUserExistUseCase(authRepoImpl),
                    loginUseCase = LoginUseCase(authRepoImpl),
                    registerUserUseCase = RegisterUserUseCase(authRepoImpl),
                    autoLoginUseCase = AutoLoginUseCase(authRepoImpl),
                    authStorageDataSource = authStorageDataSource
                ) as T
            }
        }
    }
}
