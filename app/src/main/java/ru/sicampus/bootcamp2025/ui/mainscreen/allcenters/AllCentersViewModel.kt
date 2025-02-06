package ru.sicampus.bootcamp2025.ui.mainscreen.allcenters

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import ru.sicampus.bootcamp2025.Const
import ru.sicampus.bootcamp2025.data.CenterRepositoryImpl
import ru.sicampus.bootcamp2025.data.sources.locale.CredentialsLocalDataSource
import ru.sicampus.bootcamp2025.data.sources.network.CenterNetworkDataSource
import ru.sicampus.bootcamp2025.domain.usecases.GetNearestAvailableCentersUseCase


class AllCentersViewModel(
    private val useCase: GetNearestAvailableCentersUseCase,
    private val application: Application
) : AndroidViewModel(application) {

    val listState = Pager(
        config = PagingConfig(
            pageSize = 10,
            enablePlaceholders = false,
            maxSize = 50
        )
    ) {
        CenterListPagingSource(useCase::invoke)
    }.flow
        .cachedIn(viewModelScope)

    companion object {
        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
                val application = extras[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY]!!
                return AllCentersViewModel(
                    useCase = GetNearestAvailableCentersUseCase(
                        repository = CenterRepositoryImpl(
                            networkDataSource = CenterNetworkDataSource,
                            credentialsLocalDataSource = CredentialsLocalDataSource.getInstance(
                                application.getSharedPreferences(Const.TOKEN_KEY, Context.MODE_PRIVATE)
                            )
                        )
                    ),
                    application = application
                ) as T
            }
        }
    }
}