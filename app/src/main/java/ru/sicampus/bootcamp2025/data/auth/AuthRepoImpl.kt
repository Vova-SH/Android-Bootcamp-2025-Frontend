package ru.sicampus.bootcamp2025.data.auth

import kotlinx.coroutines.delay
import ru.sicampus.bootcamp2025.domain.auth.AuthRepo

class AuthRepoImpl(
    private val authNetworkDataSource: AuthNetworkDataSource,
    private val authStorageDataSource: AuthStorageDataCource
) : AuthRepo{
    override suspend fun isUserExist(login: String): Result<Boolean> {
        delay(2000)
        return authNetworkDataSource.isUserExist(login)
    }

    override suspend fun register(login: String, password: String): Result<Unit> {
        return authNetworkDataSource.register(login, password)
    }

    override suspend fun login(login: String, password: String): Result<Unit> {
        val token = authStorageDataSource.updateToken(login, password)
        return authNetworkDataSource.login(token).onFailure {
            authStorageDataSource.clear()
        }
    }
}
