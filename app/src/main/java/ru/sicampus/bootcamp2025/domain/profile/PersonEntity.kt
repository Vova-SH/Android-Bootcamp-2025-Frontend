package ru.sicampus.bootcamp2025.domain.profile

data class PersonEntity (
    val id: Long,
    val login: String,
    val name: String,
    val email: String,
    val info: String,
    val phone: String,
    var departmentName: String?,
//    val pathtoImage: String,
)
