package ru.sicampus.bootcamp2025.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.sicampus.bootcamp2025.util.DateSerializer
import java.util.Date

@Serializable
data class UserDTO(
    @SerialName("id") val id: Long,
    @SerialName("email") val email: String,
    @SerialName("firstName") val firstName: String,
    @SerialName("lastName") val lastName: String,
    @SerialName("role") val role: String,
    @SerialName("birthDate") @Serializable(with = DateSerializer::class) val birthDate: Date?,
    @SerialName("phoneNumber") val phoneNumber: String?,
    @SerialName("telegramUsername") val telegramUsername: String?,
    @SerialName("organizationName") val organizationName: String? = null,
    @SerialName("about") val about: String?,
    @SerialName("photoUrl") val photoUrl: String?
)