package ru.sicampus.bootcamp2025.domain

class GetListsUseCase(
    private val repo: ListRepo
) {
    suspend operator fun invoke()=  repo.getUsers()
}