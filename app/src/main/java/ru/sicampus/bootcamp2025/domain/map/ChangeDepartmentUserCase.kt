package ru.sicampus.bootcamp2025.domain.map

import ru.sicampus.bootcamp2025.data.profile.PersonUpdateDto

class ChangeDepartmentUserCase(
    private val mapRepo: MapRepo
){
    operator suspend fun invoke(personUpdateDto: PersonUpdateDto): Result<Unit> {
        return mapRepo.changeDepartmentName(personUpdateDto)
    }
}