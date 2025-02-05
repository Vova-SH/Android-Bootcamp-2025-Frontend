package ru.sicampus.bootcamp2025.domain.usecases

import ru.sicampus.bootcamp2025.domain.entities.UserEntity
import ru.sicampus.bootcamp2025.domain.repositories.UserRepository

class GetLocalUser(
    private val userRepository: UserRepository
) {

    suspend operator fun invoke(): UserEntity? = userRepository.getLocalUser()
}