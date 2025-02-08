package ru.sicampus.bootcamp2025.uiList.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.sicampus.bootcamp2025.data.auth.AuthStorageDataCource
import ru.sicampus.bootcamp2025.data.list.UserNetworkDataSource
import ru.sicampus.bootcamp2025.data.list.UserRepoImpl
import ru.sicampus.bootcamp2025.domain.GetSerUseCase
import ru.sicampus.bootcamp2025.domain.UserEntity


@Suppress("UNCHECKED_CAST")
class ListViewModel(private val getUserUseCase: GetSerUseCase) : ViewModel() {

    private val _state = MutableStateFlow<State>(State.Loading)
    public val state = _state.asStateFlow()

    val listState = Pager(
        config = PagingConfig(
            pageSize = 10,
            enablePlaceholders = false,
            maxSize = 50,
        )
    ){
        ListPagingSource(getUserUseCase::invoke)
    }.flow
        .cachedIn(viewModelScope)

    init {
        updateState()
    }

    fun clickRefresh(){
        updateState()
    }

    private fun updateState(){
        viewModelScope.launch {
            _state.emit(State.Loading)
            getUserUseCase.invoke(
                pageNumb = 10,
                pageSize = 50
            ).fold(onSuccess = {
                data ->
                State.Show(data)
            },
                onFailure = {
                    error ->
                    State.Error(error.message.toString())
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
                            userNetworkDataSource = UserNetworkDataSource(), authStorageDataSource = AuthStorageDataCource
                        )
                    )
                ) as T
            }
        }
    }
}
