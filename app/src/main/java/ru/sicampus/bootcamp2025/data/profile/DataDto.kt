package ru.sicampus.bootcamp2025.data.profile

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DataDto (
    @SerialName("id")
    val id: Long,
    @SerialName("name")
    val name: String,
    @SerialName("username")
    val login: String,
    @SerialName("email")
    val email: String,
    @SerialName("info")
    val info: String?,
    @SerialName("phone")
    val phone: String?,
)