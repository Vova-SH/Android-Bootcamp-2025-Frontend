package ru.sicampus.bootcamp2025.domain.auth

class RegisterUseCase(
    private val authRepo : AuthRepo
){
    suspend operator fun invoke(email : String, password : String, name : String) : Result<Unit> {
        return authRepo.register(email, password, name).mapCatching {
            authRepo.login(email, password)
        }
    }
}