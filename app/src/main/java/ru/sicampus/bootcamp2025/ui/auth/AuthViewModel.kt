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
import ru.sicampus.bootcamp2025.R
import ru.sicampus.bootcamp2025.data.auth.AuthNetworkDataSource
import ru.sicampus.bootcamp2025.data.auth.AuthRepoImpl
import ru.sicampus.bootcamp2025.data.auth.AuthStorageDataSource
import ru.sicampus.bootcamp2025.domain.auth.IsUserExistUseCase
import ru.sicampus.bootcamp2025.domain.auth.LoginUseCase
import ru.sicampus.bootcamp2025.domain.auth.RegisterUserCase

class AuthViewModel(
    application: Application,
    private val isUserExistUseCase: IsUserExistUseCase,
    private val loginUseCase: LoginUseCase,
    private val registerUserCase: RegisterUserCase,

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
        password: String
    ) {
        viewModelScope.launch {
            _state.emit(State.Loading)
            when (isNewUser){
                true -> {
                    registerUserCase(login, password).fold(
                        onSuccess = {
                            openlist()
                        },
                        onFailure = { error ->
                            updateState(error)
                        }
                    )
                }
                false -> {
                    loginUseCase(login, password).fold(
                        onSuccess = {
                            openlist()
                        },
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

    private suspend fun openlist() {
        isNewUser = false
        viewModelScope.launch {
            _action.send(Action.GotoList)
        }
    }

    private suspend fun updateState(error: Throwable? = null) {
        _state. emit(
            getStateShow(error)
        )
    }

    private fun getStateShow(error: Throwable? = null): State.Show{

        return State.Show(
            titleText = when (isNewUser){
                true -> getApplication<Application>().getString(R.string.signup)
                false -> getApplication<Application>().getString(R.string.signin)
                null -> getApplication<Application>().getString(R.string.hello)
            },
            showPassword = (isNewUser != null),
            buttonText = when (isNewUser){
                true -> getApplication<Application>().getString(R.string.signup)
                false -> getApplication<Application>().getString(R.string.signin)
                null -> getApplication<Application>().getString(R.string.hello)
            },
            errorText = error?.toString().toString(),
        )
    }

    sealed interface State {
        data object Loading : State
        data class Show(
            val titleText : String,
            val showPassword: Boolean,
            val buttonText: String,
            val errorText: String
        ) : State
    }

    companion object {
        var Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
                val application = extras[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY]!!
                val authRepoImpl = AuthRepoImpl(
                    authNetworkDataSource = AuthNetworkDataSource,
                    authStorageDataSource = AuthStorageDataSource,
                )
                return AuthViewModel(
                    application = application,
                    isUserExistUseCase = IsUserExistUseCase(authRepoImpl),
                    loginUseCase = LoginUseCase(authRepoImpl),
                    registerUserCase = RegisterUserCase(authRepoImpl)
                ) as T
            }
        }
    }
    sealed interface Action {
        data object GotoList : Action
    }
}