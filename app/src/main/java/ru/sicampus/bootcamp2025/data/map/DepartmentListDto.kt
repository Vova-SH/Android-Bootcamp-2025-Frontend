package ru.sicampus.bootcamp2025.data.map

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DepartmentListDto (
    @SerialName("content")
    val content: List<DepartmentDto>?
)