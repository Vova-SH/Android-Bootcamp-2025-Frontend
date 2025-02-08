package ru.sicampus.bootcamp2025.domain.one

interface OneCenterRepository {
    suspend fun registerToCenter(userId: Int): Result<Boolean>
    suspend fun getAllUsers(): Result<List<UserCenter>>
}