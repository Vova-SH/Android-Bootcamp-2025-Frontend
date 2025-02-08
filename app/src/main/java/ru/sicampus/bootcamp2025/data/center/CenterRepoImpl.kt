package ru.sicampus.bootcamp2025.data.center

import ru.sicampus.bootcamp2025.domain.center.CenterEntity
import ru.sicampus.bootcamp2025.domain.center.CenterRepo

class CenterRepoImpl(
    private val centerNetworkDataSource: CenterNetworkDataSource
) : CenterRepo {
    override suspend fun getCenters(): Result<List<CenterEntity>> {

        return centerNetworkDataSource.getCenters().map { listDto ->
            listDto.mapNotNull { dto ->
                //Log.d("idDto", "${dto.name ?: return@mapNotNull null}")
                val id = dto.id ?: return@mapNotNull null
                val name = dto.name ?: return@mapNotNull null
                val description = dto.description ?: return@mapNotNull null
                CenterEntity(
                    id = id,
                    name = name,
                    description = description,
                    distance = dto.distance ?: -1.0,
                    lat = dto.lat ?: 0.0,
                    lng = dto.lng ?: 0.0
                )

            }
        }
    }
}