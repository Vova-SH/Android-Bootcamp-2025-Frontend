package ru.sicampus.bootcamp2025.domain.auth

import ru.sicampus.bootcamp2025.data.user.UserDto

interface AuthRepo {
    suspend fun isUserExist(email: String): Result<Boolean?>
    suspend fun register(email: String, password: String, name : String) : Result<UserDto>
    suspend fun login(email: String, password: String) : Result <UserDto>
}
