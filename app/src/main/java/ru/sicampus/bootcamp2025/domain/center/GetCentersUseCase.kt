package ru.sicampus.bootcamp2025.domain.center

import android.util.Log

class GetCentersUseCase (
    private val repo : CenterRepo
) {
    suspend operator fun invoke() = repo.getCenters()


}