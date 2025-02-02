package ru.sicampus.bootcamp2025.data

import kotlinx.serialization.SerialName

data class ListDto (
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String,
    @SerialName("description")
    val description: String,
    @SerialName("coordinates")
    val coordinates: String
)