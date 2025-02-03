package ru.sicampus.bootcamp2025.data

import ru.sicampus.bootcamp2025.data.dtos.ProfileDto
import ru.sicampus.bootcamp2025.data.sources.locale.ProfileLocalDataSource
import ru.sicampus.bootcamp2025.data.sources.network.ProfileNetworkDataSource
import ru.sicampus.bootcamp2025.domain.entities.ProfileEntity
import ru.sicampus.bootcamp2025.domain.repositories.ProfileRepository
import java.time.LocalTime


class ProfileRepositoryImpl(
    private val localDataSource: ProfileLocalDataSource,
    private val networkDataSource: ProfileNetworkDataSource
) : ProfileRepository {

    override suspend fun getProfileById(profileId: Int): Result<ProfileEntity> {
        val localAnswer = localDataSource.getCachedData()
        if (localAnswer != null
            && LocalTime.now().minute - localDataSource.getLastTimeUpdated().minute > 5
        )
            return map(Result.success(localAnswer))
        else {
            val networkAnswer = networkDataSource.getProfileById(profileId)
            localDataSource.cacheData(networkAnswer.getOrNull())
            return map(networkAnswer)
        }
    }

    private fun map(profileDto: Result<ProfileDto>): Result<ProfileEntity> {
        return profileDto.map { dto ->
            ProfileEntity(
                name = dto.name!!,
                lastname = dto.lastname!!,
                photoUrl =  dto.photoUrl!!,
                phoneNumber = dto.phoneNumber!!,
                email = dto.email!!
            )
        }
    }
}