package ru.sicampus.bootcamp2025.domain

interface UserRepo {
    suspend fun getUsers(): Result<List<UserEntity>>
}