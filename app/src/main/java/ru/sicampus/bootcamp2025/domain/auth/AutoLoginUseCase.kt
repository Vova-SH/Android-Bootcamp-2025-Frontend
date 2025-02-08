package ru.sicampus.bootcamp2025.domain.auth

class AutoLoginUseCase(
    private val authRepo: AuthRepo
) {
    suspend operator fun invoke(): Result<Unit> {
        return authRepo.autoLogin()
    }
}