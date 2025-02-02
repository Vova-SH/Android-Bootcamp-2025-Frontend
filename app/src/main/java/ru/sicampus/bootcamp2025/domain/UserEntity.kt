package ru.sicampus.bootcamp2025.domain
import java.util.Date

data class UserEntity (
    val id: Long,
    val email: String,
    val firstName: String,
    val lastName: String,
    val role: String,
    val birthDate: Date?,
    val phoneNumber: String?,
    val telegramUsername: String?,
    val organizationName: String? = null,
    val about: String?,
    val photoUrl: String?
)