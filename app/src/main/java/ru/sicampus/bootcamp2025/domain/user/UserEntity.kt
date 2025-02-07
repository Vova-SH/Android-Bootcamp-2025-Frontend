package ru.sicampus.bootcamp2025.domain.user

import java.sql.Timestamp

data class UserEntity(
    val id : Long,
    var email: String,
    var birthDate : String?,
    var name: String,
    var description: String?,
    var avatarUrl: String?,
    /*val joinedAt : Timestamp,
    val createdAt :  Timestamp,*/
    var center : String?,
    //var centerDescription : String,
    val authorities : String

)
