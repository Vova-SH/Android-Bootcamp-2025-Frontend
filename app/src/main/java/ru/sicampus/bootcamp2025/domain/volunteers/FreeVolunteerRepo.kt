package ru.sicampus.bootcamp2025.domain.volunteers

import ru.sicampus.bootcamp2025.domain.UserEntity

interface FreeVolunteerRepo {
    suspend fun getFreeVolunteers(): Result<List<UserEntity>>
}