package ru.sicampus.bootcamp2025.domain

class GetSerUseCase(private val repo: UserRepo)
{
    operator suspend fun invoke() = repo.getUsers()
}
