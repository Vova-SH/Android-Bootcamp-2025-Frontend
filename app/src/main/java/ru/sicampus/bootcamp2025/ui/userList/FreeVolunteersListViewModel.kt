package ru.sicampus.bootcamp2025.ui.userList

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.sicampus.bootcamp2025.domain.user.GetUserUseCase
import ru.sicampus.bootcamp2025.domain.user.UserEntity
import ru.sicampus.bootcamp2025.ui.centerList.CenterListViewModel.State

class FreeVolunteersListViewModel(
    private val getUserListUseCase: GetUserUseCase
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
            /*_state.emit(State.Loading)
            _state.emit(
                getUserListUseCase.getUserList().fold(
                    onSuccess = { data ->
                        State.Show(data)
                    },
                    onFailure = { error ->
                        State.Error(error.message.toString())
                    }
                )
            )*/
        }
    }


    sealed interface State {
        data object Loading: State
        data class Show(
            val items: List<UserEntity>
        ) : State
        data class Error(
            val text: String
        ) : State
    }
    /*companion object {
        val Factory : ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return FragmentFreeVolounteersViewModel(
                    getUserUseCase = GetUserUseCase(
                        repo = UserRepoImpl(
                            userNetworkDataSource = UserNetworkDataSource()
                        ),
                        AuthStorageDataSource
                    )
                ) as T
            }
        }
    }
*/
}