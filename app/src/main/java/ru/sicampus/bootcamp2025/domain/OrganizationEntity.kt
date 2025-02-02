package ru.sicampus.bootcamp2025.domain

data class OrganizationEntity (
    val id: Long,
    val name: String?,
    val address: String?,
    val latitude: Float,
    val longitude: Float
)