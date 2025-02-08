package ru.sicampus.bootcamp2025.domain.register

import ru.sicampus.bootcamp2025.data.UserDTO
import ru.sicampus.bootcamp2025.data.register.UserRegisterDTO

interface RegisterRepo {
    suspend fun register(registerDTO: UserRegisterDTO): Result<UserDTO>
}