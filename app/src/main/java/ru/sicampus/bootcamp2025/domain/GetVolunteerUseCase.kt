package ru.sicampus.bootcamp2025.domain

class GetVolunteerUseCase(private val repo: VolunteerRepo) {
    suspend operator fun invoke() = repo.getUsers()

}