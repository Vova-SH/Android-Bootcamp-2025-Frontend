package ru.sicampus.bootcamp.data.list

import ru.sicampus.bootcamp.data.auth.AuthStorageDataSource
import ru.sicampus.bootcamp.data.centers.CentersNetworkDataSource
import ru.sicampus.bootcamp.domain.Centers.CentersRepo
import ru.sicampus.bootcamp.domain.list.CentersEntity
import ru.sicampus.bootcamp.domain.list.UserEntity
import ru.sicampus.bootcamp.domain.list.UserRepo

class CentersRepoImpl(
    private val centersNetworkDataSource: CentersNetworkDataSource,
    private val authStorageDataSource: AuthStorageDataSource
) : CentersRepo {
    override suspend fun getCenters(): Result<List<CentersEntity>> {
        val token = authStorageDataSource.token
            ?: return Result.failure(IllegalStateException("token is null"))
        return centersNetworkDataSource.getCenters(token).map { listDto ->
            listDto.mapNotNull { dto ->
                CentersEntity(
                    name = dto.name ?: return@mapNotNull null,
                    address = dto.address ?: return@mapNotNull null,
                    lat = dto.lat?: return@mapNotNull null,
                    lon = dto.lon?: return@mapNotNull null,
                )
            }
        }
    }
}
