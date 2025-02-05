package ru.sicampus.bootcamp2025.auth

import kotlinx.coroutines.delay
import ru.sicampus.bootcamp2025.domain.auth.AuthRepo

class AuthRepoImpl: AuthRepo {

    override suspend fun register(login: String, password: String): Result<Unit> {
         return Result.failure(IllegalStateException("Not ready"))
    }

    override suspend fun login(login: String, password: String): Result<Unit> {
        return Result.failure(IllegalStateException("Not ready"))
    }

    override suspend fun isUserExist(login: String, password: String): Result<Boolean> {
        delay(2000)
        return Result.success(true)
    }



}