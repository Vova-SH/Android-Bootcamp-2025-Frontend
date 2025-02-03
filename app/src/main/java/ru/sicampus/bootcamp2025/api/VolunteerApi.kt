package ru.sicampus.bootcamp2025.api

import ru.sicampus.bootcamp2025.model.Volunteer

import retrofit2.http.GET

interface VolunteerApiService {
    @GET("volunteers") // У нас пока нема Api (запустим если что локально)
    suspend fun getVolunteers(): List<Volunteer>
}