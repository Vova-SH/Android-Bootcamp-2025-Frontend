package ru.sicampus.bootcamp.ui.list

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
import ru.sicampus.bootcamp.data.auth.AuthNetworkDataSource
import ru.sicampus.bootcamp.data.auth.AuthRepoImpl
import ru.sicampus.bootcamp.data.auth.AuthStorageDataSource
import ru.sicampus.bootcamp.domain.auth.RegisterUserUseCase
import kotlin.reflect.KClass

class RegisterViewModel(
    application: Application,
    private val registerUserUseCase: RegisterUserUseCase,

) : AndroidViewModel(application = application) {
    private val _state = MutableStateFlow<State>(State.Loading)
    val state = _state.asStateFlow()
    private val _action = Channel<Action>(
        capacity = Channel.BUFFERED,
        onBufferOverflow = BufferOverflow.DROP_OLDEST,
    )
    val action = _action.receiveAsFlow()
    fun clickNext(
        login: String,
        password: String,
        email: String,
        name: String,
        secondName:String,
        lastName:String,
        phoneNumber: String,
        info: String,
        photoUrl: String,
    ) {
        viewModelScope.launch {
            _state.emit(State.Loading)
            registerUserUseCase(login, password, email, name, secondName, lastName, phoneNumber, info, photoUrl).fold(
                onSuccess = { openAuth() },
                onFailure = { error ->
                    _state.emit(State.Error(error.message.toString()))
                })
        }
    }
    fun openAuth(){
        viewModelScope.launch { _action.send(Action.GoToAuth) }
    }


    private fun updateState(login: String,
                            password: String,
                            email: String,
                            firstName: String,
                            secondName:String,
                            lastName:String,
                            phoneNumber: String,
                            info: String,
                            telegramLink: String,
                            photoUrl: String,
                            ) {
        viewModelScope.launch {
            _state.emit(State.Loading)
            _state.emit(
                registerUserUseCase.invoke(login, password, email, firstName, secondName, lastName, phoneNumber, info, photoUrl).fold(
                    onSuccess = { data ->
                        State.Show(data)
                    },
                    onFailure = { error ->
                        State.Error(error.message.toString())
                    }
                )
            )
        }
    }
    sealed interface Action {
        data object GoToAuth : Action
    }
    sealed interface State {
        data object Loading : State
        data class Show(
            val items: Unit
        ) : State

        data class Error(
            val text: String
        ) : State
    }

    companion object {
        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: KClass<T>, extras: CreationExtras): T {
                val application = extras[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY]!!
                val authRepo = AuthRepoImpl(
                    authStorageDataSource = AuthStorageDataSource,
                    authNetworkDataSource = AuthNetworkDataSource,
                )
                return RegisterViewModel(
                    application = application,
                    registerUserUseCase = RegisterUserUseCase(
                        authRepo = authRepo,
                    )
                ) as T
            }
        }
    }
}
