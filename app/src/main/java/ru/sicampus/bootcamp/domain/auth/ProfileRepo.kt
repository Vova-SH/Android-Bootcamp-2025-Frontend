package ru.sicampus.bootcamp.domain.auth

import ru.sicampus.bootcamp.domain.list.ProfileEntity

interface ProfileRepo {
    suspend fun getData(): Result<ProfileEntity>
    suspend fun getData1(): Result<ProfileEntity>
}
