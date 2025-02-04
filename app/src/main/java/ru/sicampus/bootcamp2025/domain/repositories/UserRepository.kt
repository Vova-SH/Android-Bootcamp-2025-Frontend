package ru.sicampus.bootcamp2025.domain.repositories

import ru.sicampus.bootcamp2025.domain.entities.UserEntity

interface UserRepository {

    suspend fun isUserExist(login: String): Result<Boolean>
    suspend fun login(login: String, password: String): Result<Unit>
    suspend fun register(login: String, password: String): Result<Unit>
    suspend fun getLocalCredentials(): String?
    suspend fun authorize(token: String): Result<Unit>
    suspend fun getLocalUser(): UserEntity?
}