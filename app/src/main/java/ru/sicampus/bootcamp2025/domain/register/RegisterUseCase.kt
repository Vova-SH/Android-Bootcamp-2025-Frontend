package ru.sicampus.bootcamp2025.domain.register

class RegisterUseCase(
    private val registerRepo: RegisterRepo
) {
    suspend operator fun invoke(fullName: String, email : String,  password : String) : Result<Unit> {
        return TODO("Provide the return value")
    }
}