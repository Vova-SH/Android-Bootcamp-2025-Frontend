package ru.sicampus.bootcamp2025.domain.auth

class IsUserExistUseCase(
    private val authRepo: AuthRepo
) {
    suspend fun invoke(login: String, password:String):Result<Unit>{
        return authRepo.isUserExist(login,password).mapCatching {
            authRepo.isUserExist(login,password)
        }
    }
}