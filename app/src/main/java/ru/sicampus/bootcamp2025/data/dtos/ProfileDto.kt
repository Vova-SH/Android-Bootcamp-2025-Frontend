package ru.sicampus.bootcamp2025.data.dtos

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
class ProfileDto(
    @SerialName("centerId")
    val id: String?,
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