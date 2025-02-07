package ru.sicampus.bootcamp2025.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class UserDTO (
    @SerialName("name") //можно потом поменять если что
    val name: String,
    @SerialName("lastName")
    val lastName: String,
    @SerialName("age")
    val age: Int
)