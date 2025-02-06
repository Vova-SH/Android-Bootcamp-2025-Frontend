package ru.sicampus.bootcamp2025.data.list

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserDto (
    @SerialName("id")
    val id: String,
    @SerialName("name")
    val name: String?,
    @SerialName("email")
    val email: String?,
    @SerialName("photoUrl")
    val photoUrl: String?
)