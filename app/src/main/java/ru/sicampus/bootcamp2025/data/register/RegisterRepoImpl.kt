package ru.sicampus.bootcamp2025.data.register

import ru.sicampus.bootcamp2025.data.UserDTO
import ru.sicampus.bootcamp2025.domain.register.RegisterRepo

class RegisterRepoImpl(
    private val registerNetworkDataSource: RegisterNetworkDataSource
) : RegisterRepo {
    override suspend fun register(registerDTO: UserRegisterDTO): Result<UserDTO> {
        return registerNetworkDataSource.register(registerDTO)
    }
}