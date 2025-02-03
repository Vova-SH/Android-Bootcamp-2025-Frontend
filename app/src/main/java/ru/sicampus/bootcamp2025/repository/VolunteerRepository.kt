package ru.sicampus.bootcamp2025.repository

import ru.sicampus.bootcamp2025.api.VolunteerApiService
import ru.sicampus.bootcamp2025.model.Volunteer

class VolunteerRepository(private val apiService: VolunteerApiService) {
    suspend fun getVolunteers(): List<Volunteer>? {
        return try {
            apiService.getVolunteers() // Вызов suspend функции
        } catch (e: Exception) {
            null // Возвращаем null в случае ошибки
        }
    }
}