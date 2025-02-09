package ru.sicampus.bootcamp.data.auth

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AuthRegisterDto(
    @SerialName("name")
    val name: String,
    @SerialName("secondName")
    val secondName: String,
    @SerialName("lastName")
    val lastName: String,
    @SerialName("username")
    val username: String,
    @SerialName("password")
    val password: String,
    @SerialName("phoneNumber")
    val phoneNumber: String,
    @SerialName("organizationName")
    val organizationName: String,
    @SerialName("email")
    val email: String,
    @SerialName("info")
    val info: String,
)
