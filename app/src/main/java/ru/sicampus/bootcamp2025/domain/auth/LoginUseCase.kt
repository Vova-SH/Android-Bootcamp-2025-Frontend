package ru.sicampus.bootcamp2025.domain.auth

import ru.sicampus.bootcamp2025.data.user.UserDto


class LoginUseCase(
    private val authRepo : AuthRepo
){
    suspend operator fun invoke(email : String, password : String) : Result<UserDto> {
        return authRepo.login(email, password)
    }
}