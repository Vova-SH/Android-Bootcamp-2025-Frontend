package ru.sicampus.bootcamp2025.domain
import java.util.Date

data class UserEntity (
    val id: Long? = null,
    var email: String? = null,
    var name: String? = null,
    val role: String? = null,
    val birthDate: Date? = null,
    val phoneNumber: String? = null,
    val telegramUsername: String? = null,
    val organizationName: String? = null,
    val about: String? = null,
    val photoUrl: String? = null
)