package ru.sicampus.bootcamp.domain.auth

class RegisterUserUseCase(
    private val authRepo: AuthRepo,
) {
    suspend operator fun invoke(login: String, password: String, email: String, name: String, secondName: String, lastName: String, info: String, phoneNumber: String, photoUrl: String,): Result<Unit> {
        return authRepo.register(login, password, email, name, secondName, lastName, phoneNumber, info, photoUrl,).mapCatching {
            authRepo.login(login, password)
        }
    }
}
