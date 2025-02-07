package ru.sicampus.bootcamp2025.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.sicampus.bootcamp2025.data.UserDTO
import ru.sicampus.bootcamp2025.data.UserRepoImpl
import ru.sicampus.bootcamp2025.domain.userInfo.GetUserUseCase
import ru.sicampus.bootcamp2025.domain.userInfo.UserRepo

class MainScreenViewModel: ViewModel() {
    private val _user = MutableStateFlow<UserDTO?>(null)
    val user = _user.asStateFlow()


    fun getUser(){
        val userRepo: UserRepo = UserRepoImpl()
        val getUserUseCase = GetUserUseCase(userRepo)


        viewModelScope.launch {
            _user.update { getUserUseCase.execute() }
        }
    }
}