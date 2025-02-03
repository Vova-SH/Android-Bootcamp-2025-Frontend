package ru.sicampus.bootcamp2025.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
class UserDto (@SerialName("name") val name: String?,@SerialName("email") val email: String?,@SerialName("photoUrl") val photoUrl: String?,)
{


}
