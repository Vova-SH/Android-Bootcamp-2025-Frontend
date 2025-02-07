package ru.sicampus.bootcamp2025.ui.profile

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.sicampus.bootcamp2025.data.auth.storage.AuthStorageDataSource
import ru.sicampus.bootcamp2025.data.profile.DataDto
import ru.sicampus.bootcamp2025.data.profile.ProfileNetworkDataSource
import ru.sicampus.bootcamp2025.data.profile.ProfileRepoImpl
import ru.sicampus.bootcamp2025.domain.profile.ChangeDataByLoginUserCase
import ru.sicampus.bootcamp2025.domain.profile.DataEntity
import ru.sicampus.bootcamp2025.domain.profile.GetDataByLoginUserCase

class ProfileViewModel(
    application: Application,
    private val getDataByLoginUserCase: GetDataByLoginUserCase,
    private val changeDataByLoginUserCase: ChangeDataByLoginUserCase,
) : AndroidViewModel(application) {
    val _state = MutableStateFlow<State>(State.Loading)

    val state = _state.asStateFlow()

    fun getDataByLogin() {
        viewModelScope.launch {
            getDataByLoginUserCase.invoke().fold(
                onSuccess = { data ->
                    State.Show(data)
                    _state.emit(State.Show(data))
                    Log.d("ProfileViewMode", "state show updated")
                },
                onFailure = { throwable ->
                    Log.e("ProfileViewModel", "failed to get data ${throwable.message}")
                }
            )
        }
    }

    fun changeDataByLogin(dataEntity: DataEntity) {
        viewModelScope.launch {
            changeDataByLoginUserCase.invoke(
                DataDto(
                    id = dataEntity.id,
                    name = dataEntity.name,
                    login = dataEntity.login,
                    email = dataEntity.email,
                    info = dataEntity.info,
                    phone = dataEntity.phone
                )
            ).fold(
                onSuccess = {
                    State.Changed
                    _state.emit(State.Changed)
                    Log.d("ProfileViewModel", "changed data")
                },
                onFailure = { throwable ->
                    Log.e("ProfileViewModel", "failed to change data ${throwable.message}")
                }
            )
        }
    }

    companion object {
        var Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
                val application =
                    extras[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY]!!
                val profileRepo = ProfileRepoImpl(
                    profileNetworkDataSource = ProfileNetworkDataSource(),
                    authStorageDataSource = AuthStorageDataSource,
                )
                return ProfileViewModel(
                    application = application,
                    getDataByLoginUserCase = GetDataByLoginUserCase(profileRepo),
                    changeDataByLoginUserCase = ChangeDataByLoginUserCase(profileRepo),
                ) as T
            }
        }
    }

    sealed interface State {
        data object Loading : State
        data class Show(
            val item: DataEntity
        ) : State

        data object Changed : State
        data class Error(
            val text: String
        ) : State
    }
}
