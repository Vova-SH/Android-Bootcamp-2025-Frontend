package ru.sicampus.bootcamp2025.data

import ru.sicampus.bootcamp2025.data.dtos.CenterDto
import ru.sicampus.bootcamp2025.data.sources.locale.CenterLocaleDataSource
import ru.sicampus.bootcamp2025.data.sources.network.CenterNetworkDataSource
import ru.sicampus.bootcamp2025.domain.entities.CenterEntity
import ru.sicampus.bootcamp2025.domain.repositories.CenterRepository
import java.time.LocalTime

class CenterRepositoryImpl(
    private val networkDataSource: CenterNetworkDataSource,
    private val localeDataSource: CenterLocaleDataSource
) : CenterRepository {
    override suspend fun getCenters(): Result<List<CenterEntity>> {

        if (localeDataSource.getCachedData() != null
            && LocalTime.now().minute - localeDataSource.getLastTimeUpdated().minute > 5
        )
            return map(Result.success(localeDataSource.getCachedData()!!))
        else {
            localeDataSource.cacheData(networkDataSource.getCenters().getOrNull())
            return map(networkDataSource.getCenters())
        }
    }

    private fun map(listCenterDto: Result<List<CenterDto>>): Result<List<CenterEntity>> {
        return listCenterDto.map { listDto ->
            listDto.mapNotNull { dto ->
                CenterEntity(
                    name = dto.name ?: return@mapNotNull null,
                    phone = dto.phone ?: return@mapNotNull null,
                    address = dto.address ?: return@mapNotNull null,
                    imageUrl = dto.imageUrl ?: return@mapNotNull null,
                    tag = dto.tag ?: return@mapNotNull null,
                    distance = dto.distance ?: return@mapNotNull null
                )
            }
        }
    }
}