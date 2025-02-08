package ru.sicampus.bootcamp2025.domain.user

import ru.sicampus.bootcamp2025.data.auth.AuthStorageDataSource

class GetUserUseCase(
    private val repo: UserRepo,
    private val authStorageDataSource: AuthStorageDataSource
) {
    suspend operator fun invoke() = repo.getUser(authStorageDataSource.userInfo?.id)
    suspend fun getUserList(): Nothing = TODO() //repo.getUserList()

}