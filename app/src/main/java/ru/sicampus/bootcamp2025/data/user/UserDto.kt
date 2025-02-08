package ru.sicampus.bootcamp2025.data.user

import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.sql.Timestamp

@Serializable
data class UserDto(
    @SerialName("id")
    val id : Long?,
    @SerialName("email")
    var email: String,
    @SerialName("birthDate")
    var birthDate : String?,
    @SerialName("name")
    var name: String,
    @SerialName("description")
    var description: String?,
    @SerialName("avatarUrl")
    var avatarUrl: String?,
    @SerialName("joinedAt")
    val joinedAt: String?,
    @SerialName("createdAt")
    val createdAt: String,
    var center : String?,
    @SerialName("centerDescription")
    var centerDescription : String?,
    @SerialName("authorities")
    val authorities : String
)
