package ru.sicampus.bootcamp2025.ui.vlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.sicampus.bootcamp2025.data.FreeVolunteerNetworkDataSource
import ru.sicampus.bootcamp2025.data.FreeVolunteerRepoImpl
import ru.sicampus.bootcamp2025.domain.GetFreeVolunteersUseCase
import ru.sicampus.bootcamp2025.domain.UserEntity


class FreeVolunteersListViewModel(
    private val getFreeVolunteersUseCase: GetFreeVolunteersUseCase
) : ViewModel() {
    private val _state = MutableStateFlow<State>(State.Loading)
    val state = _state.asStateFlow()

    init {
        updateState()
    }

    fun clickRefresh(){
        updateState()
    }

    private fun updateState() {
        viewModelScope.launch {
            _state.emit(State.Loading)
            _state.emit(
                getFreeVolunteersUseCase.invoke().fold(
                    onSuccess = {
                        data -> State.Show(data)
                    },
                    onFailure = {
                        error -> State.Error("Error")
                    }
            )
            )
        }
    }

    sealed interface State{
        data object Loading: State
        data class Show(
            val items: List<UserEntity>
        ): State
        data class Error(
            val text: String
        ):State
    }
    companion object{
        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return FreeVolunteersListViewModel(
                    getFreeVolunteersUseCase = GetFreeVolunteersUseCase(
                        repo = FreeVolunteerRepoImpl(
                            freeVolunteerNetworkDataSource = FreeVolunteerNetworkDataSource()
                        )
                    )
                ) as T
            }
        }
    }
}