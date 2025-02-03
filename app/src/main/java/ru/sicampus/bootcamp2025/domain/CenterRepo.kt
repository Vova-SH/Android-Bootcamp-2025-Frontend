package ru.sicampus.bootcamp2025.domain

interface CenterRepo {
    suspend fun getCenters() : Result<List<CenterEntity>>
}