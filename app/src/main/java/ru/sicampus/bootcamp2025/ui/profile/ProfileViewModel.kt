package ru.sicampus.bootcamp2025.ui.profile

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import ru.sicampus.bootcamp2025.data.auth.storage.AuthStorageDataSource
import ru.sicampus.bootcamp2025.data.profile.DataDto
import ru.sicampus.bootcamp2025.data.profile.ProfileNetworkDataSource
import ru.sicampus.bootcamp2025.data.profile.ProfileRepoImpl
import ru.sicampus.bootcamp2025.domain.profile.ChangeDataByLoginUserCase
import ru.sicampus.bootcamp2025.domain.profile.DataEntity
import ru.sicampus.bootcamp2025.domain.profile.GetDataByLoginUserCase
import ru.sicampus.bootcamp2025.domain.profile.LogoutUserCase
import ru.sicampus.bootcamp2025.ui.auth.AuthViewModel.Action

class ProfileViewModel(
    application: Application,
    private val getDataByLoginUserCase: GetDataByLoginUserCase,
    private val changeDataByLoginUserCase: ChangeDataByLoginUserCase,
    private val logoutUserCase: LogoutUserCase,

    ) : AndroidViewModel(application) {
    val _state = MutableStateFlow<State>(State.Loading)
    val state = _state.asStateFlow()
    private val _action = Channel<Action>(
        capacity = Channel.BUFFERED,
        onBufferOverflow = BufferOverflow.DROP_OLDEST,
    )
    val action = _action.receiveAsFlow()

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
    fun logout(){
        viewModelScope.launch {
            logoutUserCase.invoke()
        }
    }
    fun openAuth(){
        viewModelScope.launch {
            _action.send(Action.GotoAuth)
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
                    logoutUserCase = LogoutUserCase(profileRepo),
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
    sealed interface Action {
        data object GotoAuth : Action
    }
}
