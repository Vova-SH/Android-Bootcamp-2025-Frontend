package ru.sicampus.bootcamp2025.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.sicampus.bootcamp2025.data.ListNetworkDataSource
import ru.sicampus.bootcamp2025.data.ListRepoImpl
import ru.sicampus.bootcamp2025.domain.GetListsUseCase
import ru.sicampus.bootcamp2025.domain.ListEntity

class ListViewModel(
    private val getUserUseCase: GetListsUseCase
) : ViewModel() {

    private val state = MutableStateFlow<State>(State.Loading)
    val _state = state.asStateFlow()

    init {
        updateState()
    }

    fun clickRefresh() {
        updateState()
    }

    private fun updateState() {
        viewModelScope.launch {
            state.emit(State.Loading)
            getUserUseCase.invoke().fold(
                onSuccess = { data ->
                    state.emit(State.Show(data))
                },
                onFailure = { error ->
                    state.emit(State.Error(error.message ?: "Unknown error"))
                }
            )
        }
    }

    sealed interface State {
        data object Loading : State
        data class Show(
            val items: List<ListEntity>
        ) : State
        data class Error(
            val text: String
        ) : State
    }

    companion object {
        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                if (modelClass.isAssignableFrom(ListViewModel::class.java)) {
                    return ListViewModel(
                        getUserUseCase = GetListsUseCase(
                            repo = ListRepoImpl(
                                userNetworkDataSource = ListNetworkDataSource()
                            )
                        )
                    ) as T
                }
                throw IllegalArgumentException("Unknown ViewModel class")
            }
        }
    }
}