package ru.sicampus.bootcamp2025.domain

interface FreeVolunteerRepo {
    suspend fun getFreeVolunteers(): Result<List<UserEntity>>
}