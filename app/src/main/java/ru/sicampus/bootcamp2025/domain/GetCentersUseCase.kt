package ru.sicampus.bootcamp2025.domain

class GetCentersUseCase (
    private val repo : CenterRepo
) {
    suspend operator fun invoke() = repo.getCenters()
}