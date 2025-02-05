package ru.sicampus.bootcamp2025.ui.list

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.sicampus.bootcamp2025.data.login.ListNetworkDataSource
import ru.sicampus.bootcamp2025.data.login.ListRepoImpl
import ru.sicampus.bootcamp2025.domain.list.GetListsUseCase
import ru.sicampus.bootcamp2025.domain.list.ListEntity
import ru.sicampus.bootcamp2025.utils.toReadableMessage

class ListViewModel(
    application: Application,
    private val getUserUseCase: GetListsUseCase
) : AndroidViewModel(application = application){

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
                    val errorMessage = error.toReadableMessage(getApplication())
                    state.emit(State.Error(errorMessage ?: "Bad Error"))
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
            override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
                if (modelClass.isAssignableFrom(ListViewModel::class.java)) {
                    val application = extras[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as Application
                    return ListViewModel(
                        application = application,
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