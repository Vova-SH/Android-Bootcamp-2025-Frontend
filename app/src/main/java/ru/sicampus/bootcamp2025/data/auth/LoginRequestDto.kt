package ru.sicampus.bootcamp2025.data.auth

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class LoginRequestDto(
    @SerialName("username")
    val username: String,
    @SerialName("password")
    val password: String
)