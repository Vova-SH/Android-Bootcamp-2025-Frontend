package ru.sicampus.bootcamp2025.domain.auth

class RegisterUserCase(
    private val authRepo: AuthRepo,
) {
    suspend operator fun invoke(login: String, password: String): Result<Unit>{
        return authRepo.register(login, password).mapCatching {
            authRepo.login(login, password)
        }
    }
}