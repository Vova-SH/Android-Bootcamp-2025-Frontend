package ru.sicampus.bootcamp2025.data.dtos

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class ProfileDto(
    @SerialName("id")
    val id: Int?,
    @SerialName("centerId")
    val centerId: Int?,
    @SerialName("name")
    val name: String?,
    @SerialName("lastname")
    val lastname: String?,
    @SerialName("photoUrl")
    val photoUrl: String?,
    @SerialName("phone_number")
    val phoneNumber: String?,
    @SerialName("email")
    val email: String?
)