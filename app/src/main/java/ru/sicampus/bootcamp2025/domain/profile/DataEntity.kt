package ru.sicampus.bootcamp2025.domain.profile

data class DataEntity (
    val id: Long,
    val login: String,
    val name: String,
    val email: String,
    val info: String,
    val phone: String,
//    val pathtoImage: String,
)
