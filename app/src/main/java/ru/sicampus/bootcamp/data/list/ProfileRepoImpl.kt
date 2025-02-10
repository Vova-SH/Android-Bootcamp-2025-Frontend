package ru.sicampus.bootcamp.data.list

import ru.sicampus.bootcamp.data.auth.AuthStorageDataSource
import ru.sicampus.bootcamp.data.auth.login
import ru.sicampus.bootcamp.domain.auth.ProfileRepo
import ru.sicampus.bootcamp.domain.list.ProfileEntity

class ProfileRepoImpl (
    private val userNetworkDataSource: UserNetworkDataSource,
    private val authStorageDataSource: AuthStorageDataSource,
    private val login: login
) : ProfileRepo {
    override suspend fun getData(): Result<ProfileEntity> {
        val token = authStorageDataSource.token
            ?: return Result.failure(IllegalStateException("token is null"))
        val login = login.login
        return userNetworkDataSource.getUserByLogin(token, login).map {  dto ->
                ProfileEntity(
                    name = dto.name,
                    email = dto.email,
                    secondName = dto.secondName,
                    lastName = dto.lastName,
                    username = dto.username,
                    phoneNumber = dto.phoneNumber ,
                    info = dto.info,
                    organizationName = dto.organizationName,
                )

        }
    }
    override suspend fun getData1(): Result<ProfileEntity> {
        val token = authStorageDataSource.token
            ?: return Result.failure(IllegalStateException("token is null"))
        val login = login.login1
        return userNetworkDataSource.getUserByLogin(token, login).map {  dto ->
            ProfileEntity(
                name = dto.name,
                email = dto.email,
                secondName = dto.secondName,
                lastName = dto.lastName,
                username = dto.username,
                phoneNumber = dto.phoneNumber ,
                info = dto.info,
                organizationName = dto.organizationName,
            )
        }
    }
}

