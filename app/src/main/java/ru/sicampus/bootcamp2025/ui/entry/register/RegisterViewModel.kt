package ru.sicampus.bootcamp2025.ui.entry.register

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.sicampus.bootcamp2025.R
import ru.sicampus.bootcamp2025.data.auth.AuthNetworkDataSource
import ru.sicampus.bootcamp2025.data.auth.AuthRepoImpl
import ru.sicampus.bootcamp2025.data.auth.AuthStorageDataSource
import ru.sicampus.bootcamp2025.domain.auth.IsUserExistUseCase
import ru.sicampus.bootcamp2025.domain.auth.RegisterUseCase
import kotlin.reflect.KClass

class RegisterViewModel(
    private val application: Application,
    private val isUserExistUseCase: IsUserExistUseCase,
    private  val registerUseCase: RegisterUseCase
) : AndroidViewModel(application = application) {
    private val _state = MutableStateFlow<State>(getStateShow())
    val state = _state.asStateFlow()

    private val _navigateToMain = MutableStateFlow(false)
    val navigateToMain = _navigateToMain.asStateFlow()

    init {
        viewModelScope.launch {
            updateState()
        }

    }
    fun register(
        email : String,
        password : String,
        name : String
    )
    {
        viewModelScope.launch {
            _state.emit(State.Loading)
            when (checkUserExistence(email)) {
                true -> {
                    updateState(getApplication<Application>().getString(R.string.error_user_exist))
                }
                false -> {
                    registerUser(email, name, password)
                }
                null -> {
                    updateState(getApplication<Application>().getString(R.string.error_unknown))
                }
            }

        }
    }

    private suspend fun checkUserExistence(email: String):Boolean?{
        return try {
            val result = isUserExistUseCase(email)
            result.fold(
                onSuccess = {isExist -> isExist},
                onFailure = {
                    Log.e("AuthViewModel", "Error checking user existence", it)
                    null
                }
            )
        } catch (e: Exception) {
            Log.e("AuthViewModel", "Error during user existence check", e)
            null
        }
    }

    private suspend fun registerUser(email: String, name: String, password: String) {
        registerUseCase(email, password, name).fold(
            onSuccess = {
                println("Register successful")
                _navigateToMain.emit(true)
                },
            onFailure = { error ->
                updateState(error.message ?: getApplication<Application>().getString(R.string.error_unknown))
            }
        )
    }

    fun changeLogin() {
        viewModelScope.launch {
            updateState()
        }
    }

    private suspend fun updateState(error : String? = null) {
        _state.emit(getStateShow(error))
    }

    private fun getStateShow(error : String? = null) : State {
        return State.Show(
            errorText = error
        )
    }


    sealed interface State {
        data object Loading : State
        data class Show(
            var errorText : String?
        ) : State
    }


    companion object {
        val Factory : ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: KClass<T>, extras: CreationExtras): T {
                val application = extras[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY]!!
                val authRepoImpl = AuthRepoImpl(
                    authNetworkDataSource = AuthNetworkDataSource,
                    authStorageDataSource = AuthStorageDataSource
                )
                return RegisterViewModel(
                    application = application,
                    isUserExistUseCase = IsUserExistUseCase(authRepoImpl),
                    registerUseCase = RegisterUseCase(authRepoImpl)
                ) as T
            }

        }
    }
}