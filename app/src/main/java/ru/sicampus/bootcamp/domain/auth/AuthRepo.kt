package ru.sicampus.bootcamp.domain.auth

interface AuthRepo {
    suspend fun register(login: String, password: String, email: String, firstName: String, secondName: String, lastName: String,  phoneNumber: String, info: String,  photoUrl: String,): Result<Unit>
    suspend fun login(login: String, password: String): Result<Unit>

}
