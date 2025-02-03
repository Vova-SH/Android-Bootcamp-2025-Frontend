package ru.sicampus.bootcamp2025.data

import ru.sicampus.bootcamp2025.domain.CenterEntity
import ru.sicampus.bootcamp2025.domain.CenterRepo

class CenterRepoImpl(
    private val centerNetworkDataSource: CenterNetworkDataSource
) : CenterRepo{
    override suspend fun getCenters(): Result<List<CenterEntity>> {
        return centerNetworkDataSource.getCenters().map { listDto ->
            listDto.mapNotNull { dto ->
                CenterEntity(
                    id = dto.id ?: return@mapNotNull null,
                    name = dto.name ?: return@mapNotNull null,
                    description = dto.description ?: return@mapNotNull null,
                    distance = dto.distance ?: return@mapNotNull null,
                    lat = dto.lat ?: return@mapNotNull null,
                    lng = dto.lng ?: return@mapNotNull null
                )
            }
        }
    }
}