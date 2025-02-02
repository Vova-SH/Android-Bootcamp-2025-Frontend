package ru.sicampus.bootcamp2025.data.dtos

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CenterDto(
    @SerialName("name")
    val name: String?,
    @SerialName("address")
    val address: String?,
    @SerialName("phone")
    val phone: String?,
    @SerialName("distance")
    val distance: Double?,
    @SerialName("tag")
    val tag: String?,
    @SerialName("imageUrl")
    val imageUrl: String?
)