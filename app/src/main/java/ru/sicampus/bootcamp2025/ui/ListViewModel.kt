package ru.sicampus.bootcamp2025.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.sicampus.bootcamp2025.data.VolunteerNetworkDataSource
import ru.sicampus.bootcamp2025.data.VolunteerRepoImpl
import ru.sicampus.bootcamp2025.domain.GetVolunteerUseCase
import ru.sicampus.bootcamp2025.domain.VolunteerEntity
import ru.sicampus.bootcamp2025.domain.VolunteerRepo

class ListViewModel(private val getVolunteerUseCase: GetVolunteerUseCase):ViewModel() {

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
                getVolunteerUseCase.invoke().fold(
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
            val items: List<VolunteerEntity>
        ) : State

        data class Error(
            val text: String
        ) : State
    }

    companion object {
        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return ListViewModel(
                    getVolunteerUseCase =  GetVolunteerUseCase(
                        repo = VolunteerRepoImpl(
                            volunteerNetworkDataSource = VolunteerNetworkDataSource()

                        )
                    )
                ) as T
            }
        }
    }


}