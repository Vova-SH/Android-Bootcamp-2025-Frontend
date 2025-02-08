package ru.sicampus.bootcamp2025.domain.repositories

import ru.sicampus.bootcamp2025.domain.entities.ProfileEntity

interface ProfileRepository {

    suspend fun getProfileById(profileId: Int): Result<ProfileEntity>
    suspend fun updateProfile(newProfile: ProfileEntity): Result<Unit>
}