package ru.sicampus.bootcamp2025.domain.one

import com.google.android.gms.maps.model.LatLng

class OneCenter(
    val id: Int,
    val name: String,
    val description: String,
    val coordinates: LatLng
)