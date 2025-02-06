package ru.sicampus.bootcamp2025.data

import ru.sicampus.bootcamp2025.data.dtos.CenterListPagingDto
import ru.sicampus.bootcamp2025.data.sources.locale.CredentialsLocalDataSource
import ru.sicampus.bootcamp2025.data.sources.network.CenterNetworkDataSource
import ru.sicampus.bootcamp2025.domain.entities.CenterEntity
import ru.sicampus.bootcamp2025.domain.repositories.CenterRepository

class CenterRepositoryImpl(
    private val networkDataSource: CenterNetworkDataSource,
    private val credentialsLocalDataSource: CredentialsLocalDataSource
) : CenterRepository {
    override suspend fun getCenters(pageNum: Int, pageSize: Int): Result<List<CenterEntity>> {
        return map(
            networkDataSource.getCenters(
                credentialsLocalDataSource.getToken() ?: "12345",
                pageNum,
                pageSize
            )
        )
    }

    private fun map(listCenterDto: Result<CenterListPagingDto>): Result<List<CenterEntity>> {
        return listCenterDto.map { listPagingDto ->
            listPagingDto.centerList?.mapNotNull { dto ->
                CenterEntity(
                    id = dto.id ?: return@mapNotNull null,
                    name = dto.name ?: return@mapNotNull null,
                    phone = dto.phone ?: return@mapNotNull null,
                    address = dto.address ?: return@mapNotNull null,
                    imageUrl = dto.imageUrl ?: return@mapNotNull null,
                    tag = dto.tag ?: return@mapNotNull null,
                    latitude = dto.latitude ?: return@mapNotNull null,
                    longitude = dto.longitude ?: return@mapNotNull null
                )
            } ?: return Result.failure(IllegalStateException("Page data cannot be null"))
        }
    }
}