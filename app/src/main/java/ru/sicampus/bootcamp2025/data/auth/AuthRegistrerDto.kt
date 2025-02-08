package ru.sicampus.bootcamp2025.data.auth

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AuthRegistrerDto(
    @SerialName("username")
    val username : String,
    @SerialName("password")
    val password : String,
    @SerialName("name")
    val name: String = username,
    @SerialName("email")
    val email: String = "$username@example.com",
)
