package ru.sicampus.bootcamp2025.data.sources.locale

import ru.sicampus.bootcamp2025.data.dtos.ProfileDto
import java.time.LocalTime

class ProfileLocalDataSource {

    private var currentProfile: ProfileDto? = null
    private var lastTimeUpdated: LocalTime = LocalTime.now()

    fun cacheData(profileDto: ProfileDto?) {
        currentProfile = profileDto
    }

    fun getCachedData(): ProfileDto? {
        return currentProfile
    }

    fun getLastTimeUpdated(): LocalTime {
        return lastTimeUpdated
    }
}