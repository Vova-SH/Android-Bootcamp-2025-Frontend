package ru.sicampus.bootcamp2025.data.auth

import ru.sicampus.bootcamp2025.data.user.UserDto
import ru.sicampus.bootcamp2025.domain.auth.AuthRepo

class AuthRepoImpl(
    private val authNetworkDataSource: AuthNetworkDataSource,
    private val authStorageDataSource: AuthStorageDataSource
) : AuthRepo{
    override suspend fun isUserExist(email: String): Result<Boolean?> {
        return authNetworkDataSource.isUserExist(email)
    }

    override suspend fun register(email: String, password: String, name : String): Result<UserDto> {
        return authNetworkDataSource.register(email, password, name)
    }

    override suspend fun login(email: String, password: String): Result<UserDto> {
        val token = authStorageDataSource.updateToken(email, password)

        val userInfo = authNetworkDataSource.login(token).onFailure {
            authStorageDataSource.clear()
        }
        authStorageDataSource.updateUserInfo(userInfo)
        return userInfo
    }

}