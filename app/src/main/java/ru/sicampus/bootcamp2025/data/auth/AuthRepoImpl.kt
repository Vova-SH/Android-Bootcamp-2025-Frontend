package ru.sicampus.bootcamp2025.data.auth

import ru.sicampus.bootcamp2025.data.save.PrefsKeys
import ru.sicampus.bootcamp2025.data.save.PrefsManager
import ru.sicampus.bootcamp2025.domain.auth.AuthRepo


class AuthRepoImpl(
    private val authNetworkDataSource: AuthNetworkDataSource,
    private val authStorageDataSource: AuthStorageDataSource,
) : AuthRepo {
    init {
        authStorageDataSource.initialize()
    }

    override suspend fun autoLogin(): Result<Unit> {
        return try {
            val token = authStorageDataSource.getCurrentToken()
                ?: return Result.failure(IllegalStateException("No saved token"))

            authNetworkDataSource.login(token).onSuccess {
                PrefsManager.getInstance().putBoolean(PrefsKeys.IS_LOGGED_IN, true)
            }
        } catch (e: Exception) {
            authStorageDataSource.clear()
            Result.failure(e)
        }
    }

    override suspend fun login(login: String, password: String): Result<Unit> {
        return try {
            val token = authStorageDataSource.updateToken(login, password)
            authNetworkDataSource.login(token).onSuccess {
                PrefsManager.getInstance().putBoolean(PrefsKeys.IS_LOGGED_IN, true)
            }.onFailure {
                authStorageDataSource.clear()
            }
        } catch (e: Exception) {
            authStorageDataSource.clear()
            Result.failure(e)
        }
    }

    override suspend fun isUserExist(login: String): Result<Boolean> {
        return authNetworkDataSource.isUserExist(login)
    }

    override suspend fun register(login: String, password: String): Result<Unit> {
        return authNetworkDataSource.register(login, password)
    }
}
