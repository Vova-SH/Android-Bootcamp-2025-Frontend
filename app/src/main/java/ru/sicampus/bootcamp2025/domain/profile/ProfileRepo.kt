package ru.sicampus.bootcamp2025.domain.profile

import ru.sicampus.bootcamp2025.domain.UserEntity

interface ProfileRepo {
    suspend fun getProfile(): Result<UserEntity>

}