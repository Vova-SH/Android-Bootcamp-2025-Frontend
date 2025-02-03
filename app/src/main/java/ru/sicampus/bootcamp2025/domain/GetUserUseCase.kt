package ru.sicampus.bootcamp2025.domain

import ru.sicampus.bootcamp2025.data.UserDTO
import ru.sicampus.bootcamp2025.data.UserRepoImpl

class GetUserUseCase(private val userRepo: UserRepo) {

    suspend fun execute(): UserDTO {
        return userRepo.fetchUser()
    }

}