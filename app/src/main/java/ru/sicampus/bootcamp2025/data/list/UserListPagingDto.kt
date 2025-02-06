package ru.sicampus.bootcamp2025.data.list

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserListPagingDto (
    @SerialName("content")
    val content: List<UserDto>?
)