package ru.sicampus.bootcamp2025.data

import ru.sicampus.bootcamp2025.data.dtos.ProfileDto
import ru.sicampus.bootcamp2025.data.sources.locale.CredentialsLocalDataSource
import ru.sicampus.bootcamp2025.data.sources.locale.ProfileLocalDataSource
import ru.sicampus.bootcamp2025.data.sources.network.ProfileNetworkDataSource
import ru.sicampus.bootcamp2025.domain.entities.ProfileEntity
import ru.sicampus.bootcamp2025.domain.repositories.ProfileRepository


class ProfileRepositoryImpl(
    private val localDataSource: ProfileLocalDataSource,
    private val networkDataSource: ProfileNetworkDataSource,
    private val credentialsLocalDataSource: CredentialsLocalDataSource
) : ProfileRepository {

    override suspend fun getProfileById(profileId: Int): Result<ProfileEntity> {
        val localAnswer = localDataSource.getCachedData()
        if (localAnswer != null)
            return map(Result.success(localAnswer))
        else {
            val networkAnswer =
                networkDataSource.getProfileById(
                    profileId,
                    credentialsLocalDataSource.getToken() ?: "1234"
                )
            if (networkAnswer.getOrNull() != null)
                localDataSource.cacheData(networkAnswer.getOrNull())
            return map(networkAnswer)
        }
    }

    private fun map(profileDto: Result<ProfileDto>): Result<ProfileEntity> {
        return profileDto.map { dto ->
            ProfileEntity(
                id = dto.id ?: return Result.failure(IllegalStateException("Null data")),
                name = dto.name ?: return Result.failure(IllegalStateException("Null data")),
                lastname = dto.lastname
                    ?: return Result.failure(IllegalStateException("Null data")),
                photoUrl = dto.photoUrl
                    ?: return Result.failure(IllegalStateException("Null data")),
                phoneNumber = dto.phoneNumber
                    ?: return Result.failure(IllegalStateException("Null data")),
                email = dto.email ?: return Result.failure(IllegalStateException("Null data")),
                centerId = dto.centerId ?: return Result.failure(IllegalStateException("Null data"))
            )
        }
    }
}