package ru.sicampus.bootcamp2025.domain.map

import ru.sicampus.bootcamp2025.data.profile.PersonUpdateDto

interface MapRepo {
    suspend fun getPlaces(): Result<List<DepartmentEntity>>
    suspend fun getPlaceByName(name: String): Result<DepartmentEntity>
    suspend fun changeDepartmentName(personUpdateDto: PersonUpdateDto): Result<Unit>

}