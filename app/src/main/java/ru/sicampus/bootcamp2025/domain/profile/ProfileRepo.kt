package ru.sicampus.bootcamp2025.domain.profile

import ru.sicampus.bootcamp2025.data.UserDTO

interface ProfileRepo {
    suspend fun getProfile(): Result<UserDTO>

}