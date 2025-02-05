package ru.sicampus.bootcamp2025.domain

import retrofit2.Call
import retrofit2.http.GET

interface VolunteerApi {
    @GET("/api/volunteer/2")
     fun getVolunteers() : Call<VolunteerEntity>

}