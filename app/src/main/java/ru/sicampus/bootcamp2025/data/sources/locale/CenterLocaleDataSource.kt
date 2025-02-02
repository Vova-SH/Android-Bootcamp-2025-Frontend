package ru.sicampus.bootcamp2025.data.sources.locale

import ru.sicampus.bootcamp2025.data.dtos.CenterDto
import java.time.LocalTime

class CenterLocaleDataSource {

    private var cachedCenters: List<CenterDto>? = null
    private var lastTimeUpdated: LocalTime = LocalTime.now()

    fun cacheData(data: List<CenterDto>?) {
        cachedCenters = data
    }

    fun getCachedData(): List<CenterDto>? {
        return cachedCenters
    }

    fun getLastTimeUpdated(): LocalTime {
        return lastTimeUpdated
    }
}