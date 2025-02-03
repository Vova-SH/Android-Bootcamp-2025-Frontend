package ru.sicampus.bootcamp2025.domain.entities

data class CenterEntity (
    val name: String,
    val address: String,
    val phone: String,
    val distance: Double,
    val tag: String,
    val imageUrl: String
)