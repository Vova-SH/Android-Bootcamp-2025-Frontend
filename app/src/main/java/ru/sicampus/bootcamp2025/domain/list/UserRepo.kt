package ru.sicampus.bootcamp2025.domain.list

interface UserRepo {
    suspend fun getUsers(): Result<List<UserEntity>>
}