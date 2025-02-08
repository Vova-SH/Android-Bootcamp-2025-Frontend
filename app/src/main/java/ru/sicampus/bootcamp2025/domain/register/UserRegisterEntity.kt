package ru.sicampus.bootcamp2025.domain.register

import java.util.Date

data class UserRegisterEntity (
    val email: String? = null,
    val name: String? = null,
    val password: String? = null,
    val phoneNumber: String? = null,
    val telegramUsername: String? = null,
    val about: String? = null
)