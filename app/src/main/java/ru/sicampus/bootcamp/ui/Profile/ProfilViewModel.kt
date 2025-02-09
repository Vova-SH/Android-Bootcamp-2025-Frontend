package ru.sicampus.bootcamp.ui.Profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.sicampus.bootcamp.data.auth.AuthStorageDataSource
import ru.sicampus.bootcamp.data.auth.login
import ru.sicampus.bootcamp.data.list.ProfileRepoImpl
import ru.sicampus.bootcamp.data.list.UserNetworkDataSource
import ru.sicampus.bootcamp.domain.list.GetProfileUseCase
import ru.sicampus.bootcamp.domain.list.ProfileEntity

class ProfilViewModel(
    private val getUsersUseCase: GetProfileUseCase
) : ViewModel() {
    private val _state = MutableStateFlow<State>(State.Loading)
    val state = _state.asStateFlow()

    init {
        updateState()
    }

    fun clickRefresh() {
        updateState()
    }

    private fun updateState() {
        viewModelScope.launch {
            _state.emit(State.Loading)
            _state.emit(
                getUsersUseCase.invoke().fold(
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

    sealed interface State {
        data object Loading : State
        data class Show(
            val items: ProfileEntity
        ) : State

        data class Error(
            val text: String
        ) : State
    }

    companion object {
        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return ProfilViewModel(
                    getUsersUseCase = GetProfileUseCase(
                        repo = ProfileRepoImpl(
                            userNetworkDataSource = UserNetworkDataSource(),
                            authStorageDataSource = AuthStorageDataSource,
                            login = login
                        )
                    )
                ) as T
            }
        }
    }
}

