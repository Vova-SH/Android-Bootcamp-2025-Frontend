package ru.sicampus.bootcamp.data.list

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProfileDto(
    @SerialName("name")
    val name: String,
    @SerialName("secondName")
    val secondName: String,
    @SerialName("lastName")
    val lastName: String,
    @SerialName("username")
    val username: String,
    @SerialName("email")
    val email: String,
    @SerialName("organizationName")
    val organizationName: String?,
    @SerialName("phoneNumber")
    val phoneNumber: String,
    @SerialName("info")
    val info: String,
)
