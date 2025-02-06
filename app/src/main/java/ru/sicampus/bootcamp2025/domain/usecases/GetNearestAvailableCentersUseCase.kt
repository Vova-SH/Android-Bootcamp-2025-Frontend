package ru.sicampus.bootcamp2025.domain.usecases

import ru.sicampus.bootcamp2025.domain.repositories.CenterRepository

class GetNearestAvailableCentersUseCase(
    private val repository: CenterRepository
) {
    suspend operator fun invoke(pageNum: Int, pageSize: Int) =
        repository.getCenters(pageNum, pageSize)
}