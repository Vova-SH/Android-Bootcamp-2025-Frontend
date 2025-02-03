package ru.sicampus.bootcamp2025.domain

import ru.sicampus.bootcamp2025.data.UserDTO

interface UserRepo {

    suspend fun fetchUser(): UserDTO
}