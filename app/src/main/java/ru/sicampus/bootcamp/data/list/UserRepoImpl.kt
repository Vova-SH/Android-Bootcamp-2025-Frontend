package ru.sicampus.bootcamp.data.list

import ru.sicampus.bootcamp.data.auth.AuthStorageDataSource
import ru.sicampus.bootcamp.domain.list.UserEntity
import ru.sicampus.bootcamp.domain.list.UserRepo

class UserRepoImpl(
    private val userNetworkDataSource: UserNetworkDataSource,
    private val authStorageDataSource: AuthStorageDataSource,
) : UserRepo {
    override suspend fun getUsers(): Result<List<UserEntity>> {
        val token = authStorageDataSource.token
            ?: return Result.failure(IllegalStateException("token is null"))
        return userNetworkDataSource.getUsers(token).map { listDto ->
            listDto.mapNotNull { dto ->
                UserEntity(
                    name = dto.name ?: return@mapNotNull null,
                    email = dto.email ?: return@mapNotNull null,
                    secondName = dto.secondName?: return@mapNotNull null,
                    lastName = dto.lastName?: return@mapNotNull null,
                    username = dto.username?: return@mapNotNull null,
                )
            }
        }
    }
}
