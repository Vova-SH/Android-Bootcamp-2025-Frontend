package ru.sicampus.bootcamp2025.data.profile

import ru.sicampus.bootcamp2025.data.auth.storage.AuthStorageDataSource
import ru.sicampus.bootcamp2025.domain.profile.DataEntity
import ru.sicampus.bootcamp2025.domain.profile.ProfileRepo

class ProfileRepoImpl(
    private val profileNetworkDataSource: ProfileNetworkDataSource,
    private val authStorageDataSource: AuthStorageDataSource,

): ProfileRepo {
    override suspend fun getMyProfileData(): Result<DataEntity> {
        val token = authStorageDataSource.token
            ?: return Result.failure(IllegalStateException("token is null"))
        return profileNetworkDataSource.getMyProfileData(token).map { dataDto ->
            DataEntity(
                id = dataDto.id,
                login = dataDto.login,
                name = dataDto.name,
                email = dataDto.email,
                info = dataDto.info ?: "",
                phone = dataDto.phone ?: "",
                departmentName = dataDto.departmentName ?: "",
            )
        }
    }

    override suspend fun changeDataByLogin(personDto: PersonDto): Result<Unit> {
        val token = authStorageDataSource.token
            ?: return Result.failure(IllegalStateException("token is null"))
        return profileNetworkDataSource.changeDataByLogin(token, personDto)
        }
    override suspend fun logout(){
        authStorageDataSource.clear()
    }

    }