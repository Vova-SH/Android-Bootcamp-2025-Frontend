package ru.sicampus.bootcamp.domain.Centers

import ru.sicampus.bootcamp.domain.list.CentersEntity

interface CentersRepo {
    suspend fun getCenters(): Result<List<CentersEntity>>
}
