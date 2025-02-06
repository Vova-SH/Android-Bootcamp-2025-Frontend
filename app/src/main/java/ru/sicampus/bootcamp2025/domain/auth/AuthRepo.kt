package ru.sicampus.bootcamp2025.domain.auth

interface AuthRepo {
    suspend fun isUserExist(login: String): Result<Boolean>
    suspend fun register(login: String, password: String): Result<Unit>
    suspend fun login(login: String, password: String): Result<Unit>
    suspend fun autoLogin(): Result<Unit>
}
