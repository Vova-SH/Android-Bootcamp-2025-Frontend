package ru.sicampus.bootcamp2025.domain.auth

class LoginUseCase(
    private val authRepo: AuthRepo
) {
    suspend fun invoke(login: String, password:String):Result<Unit>{
        return authRepo.login(login,password).mapCatching {
            authRepo.login(login,password)
        }
    }
}