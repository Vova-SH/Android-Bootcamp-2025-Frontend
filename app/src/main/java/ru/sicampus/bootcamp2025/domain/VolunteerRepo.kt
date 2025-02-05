package ru.sicampus.bootcamp2025.domain

interface VolunteerRepo {
    suspend fun getUsers(): Result<List<VolunteerEntity>>
}