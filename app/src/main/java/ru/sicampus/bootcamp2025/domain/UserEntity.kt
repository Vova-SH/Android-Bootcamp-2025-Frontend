package ru.sicampus.bootcamp2025.domain

import android.provider.ContactsContract.CommonDataKinds.Email

data class UserEntity (
    val id: String,
    val name: String,
    val email: String,
    val photoUrl: String,
)
