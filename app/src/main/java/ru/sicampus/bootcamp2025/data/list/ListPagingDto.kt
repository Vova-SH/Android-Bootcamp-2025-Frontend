package ru.sicampus.bootcamp2025.data.list

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ListPagingDto(
    @SerialName("content")
    val content: List<ListDto>?
)
