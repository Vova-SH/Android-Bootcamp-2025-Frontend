package ru.sicampus.bootcamp2025.domain.usecases

import ru.sicampus.bootcamp2025.domain.repositories.UserRepository

class RegisterUseCase(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(login: String, password: String): Result<Unit> {
        userRepository.register(login, password)
        return LoginUseCase(userRepository).invoke(login, password)
    }
}