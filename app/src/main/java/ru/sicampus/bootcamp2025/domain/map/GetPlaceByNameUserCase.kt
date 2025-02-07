package ru.sicampus.bootcamp2025.domain.map

class GetPlaceByNameUserCase(
    private val mapRepo: MapRepo
) {
    suspend fun getPlaceByName(name:String): Result<DepartmentEntity> {
        return mapRepo.getPlaceByName(name)
    }
}
