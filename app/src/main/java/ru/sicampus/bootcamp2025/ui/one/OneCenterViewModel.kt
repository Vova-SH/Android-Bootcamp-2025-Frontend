package ru.sicampus.bootcamp2025.ui.one

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.sicampus.bootcamp2025.domain.one.OneCenter
import ru.sicampus.bootcamp2025.domain.one.OneCenterRepository
import ru.sicampus.bootcamp2025.domain.one.UserCenter

class OneCenterViewModel(
    private val repository: OneCenterRepository,
    val oneCenter: OneCenter
) : ViewModel() {

    private val _state = MutableLiveData<State>(State.Loading)
    val state: LiveData<State> = _state

    init {
        loadInitialData()
    }

    private fun loadInitialData() {
        viewModelScope.launch {
            _state.value = State.Loading
            loadUsers()
        }
    }

    fun registerUser(userId: Int) {
        viewModelScope.launch {
            _state.value = State.Loading
            repository.registerToCenter(userId).fold(
                onSuccess = { success ->
                    if (success) {
                        loadUsers()
                    } else {
                        _state.value = State.Error("Registration failed")
                    }
                },
                onFailure = { error ->
                    _state.value = State.Error(error.toString())
                }
            )
        }
    }

    private suspend fun loadUsers() {
        repository.getAllUsers()
            .fold(
                onSuccess = { users ->
                    _state.value = State.Loaded(
                        center = oneCenter,
                        volunteers = users,
                        isLoading = false
                    )
                },
                onFailure = { error ->
                    _state.value = State.Error(error.toString())
                }
            )
    }

    sealed interface State {
        data object Loading : State
        data class Loaded(
            val center: OneCenter,
            val volunteers: List<UserCenter>,
            val isLoading: Boolean
        ) : State
        data class Error(val message: String) : State
    }

    class OneCenterViewModelFactory(
        private val oneCenter: OneCenter,
        private val repository: OneCenterRepository
    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return OneCenterViewModel(repository, oneCenter) as T
        }
    }
}