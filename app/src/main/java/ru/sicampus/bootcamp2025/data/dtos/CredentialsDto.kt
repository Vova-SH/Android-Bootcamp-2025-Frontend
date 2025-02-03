package ru.sicampus.bootcamp2025.data.dtos

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class CredentialsDto(
    @SerialName("login")
    val login: String,
    @SerialName("password")
    val password: String
)