package ru.sicampus.bootcamp2025.data

import ru.sicampus.bootcamp2025.data.dtos.CenterDto
import ru.sicampus.bootcamp2025.data.sources.locale.CenterLocalDataSource
import ru.sicampus.bootcamp2025.data.sources.locale.CredentialsLocalDataSource
import ru.sicampus.bootcamp2025.data.sources.network.CenterNetworkDataSource
import ru.sicampus.bootcamp2025.domain.entities.CenterEntity
import ru.sicampus.bootcamp2025.domain.repositories.CenterRepository
import java.time.LocalTime

class CenterRepositoryImpl(
    private val networkDataSource: CenterNetworkDataSource,
    private val localDataSource: CenterLocalDataSource,
    private val credentialsLocalDataSource: CredentialsLocalDataSource
) : CenterRepository {
    override suspend fun getCenters(): Result<List<CenterEntity>> {

        if (localDataSource.getCachedData() != null
            && LocalTime.now().minute - localDataSource.getLastTimeUpdated().minute > 5
        )
            return map(Result.success(localDataSource.getCachedData()!!))
        else {
            localDataSource.cacheData(networkDataSource.getCenters(credentialsLocalDataSource.getToken()!!).getOrNull())
            return map(networkDataSource.getCenters(credentialsLocalDataSource.getToken()!!))
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
                    latitude = dto.latitude ?: return@mapNotNull null,
                    longitude = dto.longitude ?: return@mapNotNull null
                )
            }
        }
    }
}