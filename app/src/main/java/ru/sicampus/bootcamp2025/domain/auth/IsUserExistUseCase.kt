package ru.sicampus.bootcamp2025.domain.auth

class IsUserExistUseCase(
    private val authRepo : AuthRepo) {
    suspend operator fun invoke(email : String) : Result<Boolean?> {
        return authRepo.isUserExist(email)
    }
}