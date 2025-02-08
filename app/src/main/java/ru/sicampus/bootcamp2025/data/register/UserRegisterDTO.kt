package ru.sicampus.bootcamp2025.data.register

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserRegisterDTO (
    @SerialName("email") val email: String,
    @SerialName("name") val name: String,
    @SerialName("password") val password: String,
    @SerialName("phoneNumber") val phoneNumber: String?,
    @SerialName("telegramUsername") val telegramUsername: String?,
    @SerialName("about") val about: String?
)