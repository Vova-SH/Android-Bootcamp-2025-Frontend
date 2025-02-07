package ru.sicampus.bootcamp2025.domain.userInfo

import ru.sicampus.bootcamp2025.data.UserDTO

class GetUserUseCase(private val userRepo: UserRepo) {

    suspend fun execute(): UserDTO {
        return userRepo.fetchUser()
    }

}