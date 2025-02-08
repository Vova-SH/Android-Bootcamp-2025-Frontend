package ru.sicampus.bootcamp2025.data.map

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DepartmentDto(
    @SerialName("id")
    var id: Long,
    @SerialName("name")
    var name: String,
    @SerialName("place")
    var place: PlaceDto,
)