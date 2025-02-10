package ru.sicampus.bootcamp.domain.list

data class ProfileEntity(
    val name: String,
    val secondName: String,
    val lastName: String,
    val username: String,
    val email: String,
    val organizationName: String?,
    val phoneNumber: String,
    val info: String,
    )
