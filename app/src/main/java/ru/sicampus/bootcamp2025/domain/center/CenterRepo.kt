package ru.sicampus.bootcamp2025.domain.center

interface CenterRepo {
    suspend fun getCenters() : Result<List<CenterEntity>>
}