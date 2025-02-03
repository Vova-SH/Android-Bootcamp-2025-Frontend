package ru.sicampus.bootcamp2025.ui.mainscreen.profileinfo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.sicampus.bootcamp2025.data.ProfileRepositoryImpl
import ru.sicampus.bootcamp2025.data.sources.locale.ProfileLocalDataSource
import ru.sicampus.bootcamp2025.data.sources.network.ProfileNetworkDataSource
import ru.sicampus.bootcamp2025.domain.entities.ProfileEntity
import ru.sicampus.bootcamp2025.domain.usecases.GetProfileByIdUseCase

class ProfileViewModel(private val useCase: GetProfileByIdUseCase) : ViewModel() {
    private val _state = MutableStateFlow<State>(State.Loading)
    val state = _state.asStateFlow()

    init {
        // updateState()
    }

    private fun updateState(userId: Int) {
        viewModelScope.launch {
            _state.emit(State.Loading)
            _state.emit(
                useCase.invoke(userId).fold(
                    onSuccess = { data -> State.Show(data) },
                    onFailure = { error -> State.Error(error.message.toString()) }
                )
            )
        }
    }

    sealed interface State {
        data object Loading : State
        data class Show(val items: ProfileEntity) : State
        data class Error(val text: String) : State
    }

    companion object {
        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return ProfileViewModel(
                    useCase = GetProfileByIdUseCase(
                        repository = ProfileRepositoryImpl(
                            networkDataSource = ProfileNetworkDataSource(),
                            localDataSource = ProfileLocalDataSource()
                        )
                    )
                ) as T
            }
        }
    }

}