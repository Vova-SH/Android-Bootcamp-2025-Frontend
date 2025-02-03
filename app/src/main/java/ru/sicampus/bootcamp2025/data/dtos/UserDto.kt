package ru.sicampus.bootcamp2025.data.dtos

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class UserDto(
    @SerialName("id")
    val userId: String,
    @SerialName("role_id")
    val roleId: String,
    @SerialName("profile_id")
    val profileId: String
)