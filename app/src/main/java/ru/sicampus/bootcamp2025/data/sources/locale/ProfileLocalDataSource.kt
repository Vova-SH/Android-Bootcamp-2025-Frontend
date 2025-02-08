package ru.sicampus.bootcamp2025.data.sources.locale

import ru.sicampus.bootcamp2025.data.dtos.ProfileDto

object ProfileLocalDataSource {

    private var currentProfile: ProfileDto? = null

    fun cacheData(profileDto: ProfileDto?) {
        currentProfile = profileDto
    }

    fun getCachedData(): ProfileDto? {
        return currentProfile
    }

}