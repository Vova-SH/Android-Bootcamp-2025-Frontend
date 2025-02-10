package ru.sicampus.bootcamp.data.auth

import ru.sicampus.bootcamp.domain.auth.AuthRepo

class AuthRepoImpl(
    private val authNetworkDataSource: AuthNetworkDataSource,
    private val authStorageDataSource: AuthStorageDataSource,
) : AuthRepo {

    override suspend fun register(
        login: String,
        password: String,
        email: String,
        name: String,
        secondName: String,
        lastName: String,
        phoneNumber: String,
        info: String,
        photoUrl: String,
    ): Result<Unit> {
        return authNetworkDataSource.register(login, password, email, name, secondName, lastName, info, phoneNumber, photoUrl)
    }

    override suspend fun login(login: String, password: String): Result<Unit> {
        val token = authStorageDataSource.updateToken(login, password)
        return authNetworkDataSource.login(token).onFailure {
            authStorageDataSource.clear()
        }
    }
}
