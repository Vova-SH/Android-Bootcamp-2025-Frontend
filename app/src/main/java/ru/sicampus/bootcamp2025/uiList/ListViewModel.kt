package ru.sicampus.bootcamp2025.uiList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.sicampus.bootcamp2025.data.UserNetworkDataSource
import ru.sicampus.bootcamp2025.data.UserRepoImpl
import ru.sicampus.bootcamp2025.domain.GetSerUseCase
import ru.sicampus.bootcamp2025.domain.UserEntity
import ru.sicampus.bootcamp2025.domain.UserRepo


@Suppress("UNCHECKED_CAST")
class ListViewModel(private val getUserUseCase: GetSerUseCase) : ViewModel() {

    private val _state = MutableStateFlow<State>(State.Loading)
    public val state = _state.asStateFlow()

    init {
        updateState()
    }

    fun clickRefresh(){
        updateState()
    }

    private fun updateState(){
        viewModelScope.launch {
            _state.emit(State.Loading)
            getUserUseCase.invoke().fold(onSuccess = {
                data -> State.Show(data)
            },
                onFailure = {
                    error -> State.Error(error.message.toString())
                })
        }
    }

    sealed interface State{
        data object Loading: State
        data class Show(
            val items: List<UserEntity>
        ): State
        data class Error(
            val text: String
        ): State
    }

    companion object {
        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory{
            override fun <T : ViewModel> create(modelClass: Class<T>): T{
                return ListViewModel(
                    getUserUseCase = GetSerUseCase(
                        repo = UserRepoImpl(
                            userNetworkDataSource = UserNetworkDataSource()
                        )
                    )
                ) as T
            }
        }
    }
}
