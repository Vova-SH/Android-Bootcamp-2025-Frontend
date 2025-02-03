package ru.sicampus.bootcamp2025.domain

class GetFreeVolunteersUseCase (
    private val repo: FreeVolunteerRepo
){
    suspend operator fun invoke() = repo.getFreeVolunteers()
}

