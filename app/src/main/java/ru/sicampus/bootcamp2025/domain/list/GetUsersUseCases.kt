package ru.sicampus.bootcamp2025.domain.list

class GetUsersUseCases (
    private val repo: UserRepo
) {
    operator suspend fun invoke() = repo.getUsers()
}
