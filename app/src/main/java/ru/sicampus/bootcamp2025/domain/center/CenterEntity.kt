package ru.sicampus.bootcamp2025.domain.center

import kotlinx.serialization.Serializable

@Serializable
data class CenterEntity(
    val id : Long,
    val name: String,
    val description: String,
    val distance : Double?,
    val lat : Double?,
    val lng : Double?,
    //val users : List<UserEntity>
)