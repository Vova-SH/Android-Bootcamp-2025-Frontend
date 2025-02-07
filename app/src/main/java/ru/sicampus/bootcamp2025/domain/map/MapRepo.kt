package ru.sicampus.bootcamp2025.domain.map

import ru.sicampus.bootcamp2025.data.map.DepartmentListDto
import ru.sicampus.bootcamp2025.domain.list.UserEntity

interface MapRepo {
    suspend fun getPlaces(): Result<List<DepartmentEntity>>
    suspend fun getPlaceByName(name: String): Result<DepartmentEntity>
}