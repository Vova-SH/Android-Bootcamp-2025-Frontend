    package ru.sicampus.bootcamp.ui.list

    import androidx.lifecycle.ViewModel
    import androidx.lifecycle.ViewModelProvider
    import androidx.lifecycle.viewModelScope
    import kotlinx.coroutines.flow.MutableStateFlow
    import kotlinx.coroutines.flow.asStateFlow
    import kotlinx.coroutines.launch
    import ru.sicampus.bootcamp.data.auth.AuthStorageDataSource
    import ru.sicampus.bootcamp.data.centers.CentersNetworkDataSource
    import ru.sicampus.bootcamp.data.list.CentersRepoImpl
    import ru.sicampus.bootcamp.domain.Centers.CentersUserUseCase
    import ru.sicampus.bootcamp.domain.list.CentersEntity

    class CenterListViewModel(
        private val centersUserUseCase: CentersUserUseCase
    ) : ViewModel() {
        private val _state = MutableStateFlow<State>(State.Loading)
        val state = _state.asStateFlow()

        init {
            updateState()
        }

        fun clickRefresh() {
            updateState()
        }

        public fun updateState() {
            viewModelScope.launch {
                _state.emit(State.Loading)
                _state.emit(
                    centersUserUseCase.invoke().fold(
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
                val items: List<CentersEntity>
            ) : State

            data class Error(
                val text: String
            ) : State
        }

        companion object {
            val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return CenterListViewModel(
                        centersUserUseCase = CentersUserUseCase(
                            centersRepoImpl = CentersRepoImpl(
                                centersNetworkDataSource = CentersNetworkDataSource(),
                                authStorageDataSource = AuthStorageDataSource
                            )
                        )
                    ) as T
                }
            }
        }
    }
