package ru.sicampus.bootcamp2025.domain

import com.google.android.gms.maps.model.LatLng

data class ListEntity(
    val id: Int,
    val name: String,
    val description: String,
    val coordinates: LatLng,
)