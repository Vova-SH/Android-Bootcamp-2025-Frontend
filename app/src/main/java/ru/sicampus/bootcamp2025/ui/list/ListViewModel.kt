package ru.sicampus.bootcamp2025.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import ru.sicampus.bootcamp2025.data.list.UserNetworkDataSource
import ru.sicampus.bootcamp2025.data.list.ListRepoImpl
import ru.sicampus.bootcamp2025.domain.list.ListUseCase

class ListViewModel(
    private val getUsersUseCase: ListUseCase
) : ViewModel() {
    val listState = Pager(
        config = PagingConfig(
            pageSize = 10,
            enablePlaceholders = false,
            maxSize = 30
        )
    ) {
        ListPagingSource(getUsersUseCase::invoke)
    }.flow
        .cachedIn(viewModelScope)

    companion object {
        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return ListViewModel(
                    getUsersUseCase = ListUseCase(
                        repo = ListRepoImpl(
                            userNetworkDataSource = UserNetworkDataSource()
                        )
                    )
                ) as T
            }
        }
    }
}
