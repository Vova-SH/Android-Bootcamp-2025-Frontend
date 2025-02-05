package ru.sicampus.bootcamp2025.ui.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.sicampus.bootcamp2025.data.UserDTO
import ru.sicampus.bootcamp2025.domain.UserEntity
import kotlin.reflect.KClass

@Suppress("UNCHECKED_CAST")
class RegisterViewModel : ViewModel() {

    private val _state = MutableStateFlow<State>(getStateShow())
    val state = _state.asStateFlow()
    private val _userData = MutableStateFlow(UserEntity())
    val userData = _userData.asStateFlow()
    private var _password: String? = null
    val password: String? get() = _password

    private val isFieldsEmpty = true


    fun clickNext(
        name : String,
        email : String,
        password : String
    ){
        val user = userData.value
        user.email = email
        user.name = name
        _password = password
        viewModelScope.launch{
            _state.emit(State.Loading)
        }
        println(user)
        updateUserData(user)
    }
    init {
        _userData.value = UserEntity()
    }

    private suspend fun updateState(){
        _state.emit(
            getStateShow()
        )
    }
    private fun getStateShow() : State.Show{
        return State.Show(
            errorText = "Error"
        )
    }
    sealed interface State {
        data object Loading : State
        data class Show(
            val errorText: String?
        ): State
    }
    fun updateUserData(newData: UserEntity) {
        _userData.value = newData
    }
    fun getUserDTO(): UserDTO? {
        val user = _userData.value
        return UserDTO(
            id = user.id,
            email = user.email.toString(),
            name = user.name.toString(),
            role = user.role.toString(),
            birthDate = user.birthDate,
            phoneNumber = user.phoneNumber,
            telegramUsername = user.telegramUsername,
            organizationName = user.organizationName,
            about = user.about,
            photoUrl = user.photoUrl
        )
    }
    fun clearPassword(){
        _password = null
    }

//    companion object{
//        val Factory : ViewModelProvider.Factory = object : ViewModelProvider.Factory {
//           fun <T : ViewModel> create(modelClass: KClass<T>, extras: CreationExtras): T {
//                val application = extras[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY]!!
//                val authRepoImpl = AuthRepoImpl(
//                    authStorageDataSource = AuthStorageDataSource,
//                    authNetworkDataSource = AuthNetworkDataSource,
//                )
//              return RegisterViewModel(
//                    application = application,
//                    isUserExistUseCase = IsUserExistUseCase(authRepoImpl),
//                    loginUseCase = LoginUseCase(authRepoImpl),
//                    registerUserUseCase = RegisterUserUseCase(authRepoImpl),
//                ) as T
//            }
//        }
//    }
}