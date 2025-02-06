package ru.sicampus.bootcamp2025.data.auth

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AuthRegisterDto(
    @SerialName("email")
    var email : String?,
    @SerialName("password")
    var password : String?,
    @SerialName("birthDate")
    var birthDate : String?,
    @SerialName("name")
    var name : String?,
    @SerialName("description")
    var description : String?,
    @SerialName("avatarUrl")
    var avatarUrl : String?
)
