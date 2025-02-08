package ru.sicampus.bootcamp2025.ui.mainscreen.profileinfo

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.sicampus.bootcamp2025.Const
import ru.sicampus.bootcamp2025.data.ProfileRepositoryImpl
import ru.sicampus.bootcamp2025.data.UserRepositoryImpl
import ru.sicampus.bootcamp2025.data.sources.locale.CredentialsLocalDataSource
import ru.sicampus.bootcamp2025.data.sources.locale.ProfileLocalDataSource
import ru.sicampus.bootcamp2025.data.sources.locale.UserLocalDataSource
import ru.sicampus.bootcamp2025.data.sources.network.ProfileNetworkDataSource
import ru.sicampus.bootcamp2025.data.sources.network.UserNetworkDataSource
import ru.sicampus.bootcamp2025.domain.entities.ProfileEntity
import ru.sicampus.bootcamp2025.domain.usecases.GetLocalUserUseCase
import ru.sicampus.bootcamp2025.domain.usecases.GetProfileByIdUseCase

class ProfileViewModel(
    private val getLocalUserUseCase: GetLocalUserUseCase,
    private val getProfileByIdUseCase: GetProfileByIdUseCase,
    private val application: Application
) : AndroidViewModel(application) {
    private val _state = MutableStateFlow<State>(State.Loading)
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            updateState( getLocalUserUseCase.invoke()?.profileId ?: 1)
        }
    }

    fun onSaveChanges(newProfile: ProfileEntity) {
        // TODO: Add profile saving
    }

    fun onRefresh() {
        viewModelScope.launch {
            updateState(getLocalUserUseCase.invoke()?.profileId ?: 1)
        }
    }

    private fun updateState(userId: Int) {
        viewModelScope.launch {
            _state.emit(State.Loading)
            _state.emit(
                getProfileByIdUseCase.invoke(userId).fold(
                    onSuccess = { data -> State.Show(data) },
                    onFailure = { error -> State.Error(error.message.toString()) }
                )
            )
        }
    }

    sealed interface State {
        data object Loading : State
        data class Show(val profile: ProfileEntity) : State
        data class Error(val text: String) : State
    }

    companion object {
        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
                val application =
                    extras[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY]!!
                val credentialsLocalDataSource = CredentialsLocalDataSource.getInstance(
                    application.getSharedPreferences(
                        Const.TOKEN_KEY,
                        Context.MODE_PRIVATE
                    )
                )
                return ProfileViewModel(
                    getProfileByIdUseCase = GetProfileByIdUseCase(
                        repository = ProfileRepositoryImpl(
                            networkDataSource = ProfileNetworkDataSource,
                            localDataSource = ProfileLocalDataSource,
                            credentialsLocalDataSource = credentialsLocalDataSource
                        ),
                    ),
                    getLocalUserUseCase = GetLocalUserUseCase(
                        userRepository = UserRepositoryImpl(
                            userLocalDataSource = UserLocalDataSource,
                            credentialsLocalDataSource = credentialsLocalDataSource,
                            userNetworkDataSource = UserNetworkDataSource
                        )
                    ),
                    application = application
                ) as T
            }
        }
    }

}