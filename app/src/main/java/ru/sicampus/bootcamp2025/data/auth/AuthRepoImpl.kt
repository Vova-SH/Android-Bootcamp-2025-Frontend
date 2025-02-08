package ru.sicampus.bootcamp2025.data.auth

import ru.sicampus.bootcamp2025.data.save.PrefsKeys
import ru.sicampus.bootcamp2025.data.save.PrefsManager
import ru.sicampus.bootcamp2025.domain.auth.AuthRepo

class AuthRepoImpl(
    private val authNetworkDataSource: AuthNetworkDataSource,
    private val authStorageDataSource: AuthStorageDataSource
) : AuthRepo {


    override suspend fun autoLogin(): Result<Unit> {
        return try {
            val credentials = authStorageDataSource.getCredentials()

            authNetworkDataSource.login(credentials.first, credentials.second).onSuccess {
                PrefsManager.getInstance().putBoolean(PrefsKeys.IS_LOGGED_IN, true)
            }
        } catch (e: Exception) {
            authStorageDataSource.clear()
            Result.failure(e)
        }
    }

    override suspend fun login(login: String, password: String): Result<Unit> {
        return try {
            authNetworkDataSource.login(login, password).onSuccess {
                authStorageDataSource.saveCredentials(login, password)
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

    override suspend fun register(login: String, password: String, name: String, email: String): Result<Unit> {
        return authNetworkDataSource.register(login, password, name, email)
            .onSuccess {
                authStorageDataSource.saveCredentials(login, password)
                authStorageDataSource.saveUserDetails(name, email)
                PrefsManager.getInstance().putBoolean(PrefsKeys.IS_LOGGED_IN, true)
            }
    }
}