package ru.sicampus.bootcamp2025.data.one

import ru.sicampus.bootcamp2025.domain.one.OneCenterRepository
import ru.sicampus.bootcamp2025.domain.one.UserCenter

class OneCenterImpl(
    private val dataSource: OneNetworkDataSource,
    private val centerName: String
) : OneCenterRepository {

    override suspend fun registerToCenter(userId: Int): Result<Boolean> = runCatching {
        dataSource.registerUserToCenter(userId, centerName)
    }

    override suspend fun getAllUsers(): Result<List<UserCenter>> = runCatching {
        dataSource.getAllUsers()
    }
}