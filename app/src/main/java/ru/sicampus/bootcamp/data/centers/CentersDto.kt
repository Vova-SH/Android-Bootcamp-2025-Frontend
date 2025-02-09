package ru.sicampus.bootcamp.data.centers

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class CentersDto(
    @SerialName("name")
    val name: String,
    @SerialName("address")
    val address: String,
    @SerialName("lon")
    val lon: String,
    @SerialName("lat")
    val lat: String,
)
