package ru.sicampus.bootcamp2025.ui.mainscreen.allcenters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.sicampus.bootcamp2025.data.CenterRepositoryImpl
import ru.sicampus.bootcamp2025.data.sources.locale.CenterLocaleDataSource
import ru.sicampus.bootcamp2025.data.sources.network.CenterNetworkDataSource
import ru.sicampus.bootcamp2025.domain.entities.CenterEntity
import ru.sicampus.bootcamp2025.domain.usecases.GetNearestAvailableCentersUseCase


class AllCentersViewModel(private val useCase: GetNearestAvailableCentersUseCase) : ViewModel() {
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
                useCase.invoke().fold(
                    onSuccess = { data -> State.Show(data) },
                    onFailure = { error -> State.Error(error.message.toString()) }
                )
            )
        }
    }

    sealed interface State {
        data object Loading : State
        data class Show(val items: List<CenterEntity>) : State
        data class Error(val text: String) : State
    }

    companion object {
        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return AllCentersViewModel(
                    useCase = GetNearestAvailableCentersUseCase(
                        repository = CenterRepositoryImpl(
                            networkDataSource = CenterNetworkDataSource(),
                            localeDataSource = CenterLocaleDataSource()
                        )
                    )
                ) as T
            }
        }
    }
}