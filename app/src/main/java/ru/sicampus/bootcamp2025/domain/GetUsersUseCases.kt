package ru.sicampus.bootcamp2025.domain

class GetUsersUseCases (
    private val repo: UserRepo
) {
    operator suspend fun invoke() = repo.getUsers()
}
