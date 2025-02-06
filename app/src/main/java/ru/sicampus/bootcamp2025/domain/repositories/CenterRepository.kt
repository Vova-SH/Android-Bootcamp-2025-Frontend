package ru.sicampus.bootcamp2025.domain.repositories

import ru.sicampus.bootcamp2025.domain.entities.CenterEntity

interface CenterRepository {
    suspend fun getCenters(pageNum: Int, pageSize: Int): Result<List<CenterEntity>>
}