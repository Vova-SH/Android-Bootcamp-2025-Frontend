package ru.sicampus.bootcamp2025.domain.auth

import kotlin.math.log

class RegisterUseCase(
    private val authRepo: AuthRepo
) {
    suspend fun register(login: String, password:String):Result<Unit>{
        return authRepo.register(login,password).mapCatching {
            authRepo.register(login,password)
        }
    }
}