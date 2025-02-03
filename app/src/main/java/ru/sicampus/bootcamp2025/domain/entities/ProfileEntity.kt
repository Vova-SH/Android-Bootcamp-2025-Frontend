package ru.sicampus.bootcamp2025.domain.entities

data class ProfileEntity(
    val name: String,
    val lastname: String,
    val photoUrl: String,
    val phoneNumber: String,
    val email: String
)
