package ru.sicampus.bootcamp2025.domain.profile

import ru.sicampus.bootcamp2025.data.profile.PersonDto

interface ProfileRepo {
    suspend fun getMyProfileData(): Result<PersonEntity>
    suspend fun changeDataByLogin(personDto: PersonDto): Result<Unit>
    suspend fun logout()
}