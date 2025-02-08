package ru.sicampus.bootcamp2025.domain.user

interface UserRepo {
    suspend fun getUser(id: Long?) : Result<UserEntity>
}