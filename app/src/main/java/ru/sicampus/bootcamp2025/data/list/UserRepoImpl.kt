package ru.sicampus.bootcamp2025.data.list

import ru.sicampus.bootcamp2025.data.auth.storage.AuthStorageDataSource
import ru.sicampus.bootcamp2025.domain.list.UserEntity
import ru.sicampus.bootcamp2025.domain.list.UserRepo

class UserRepoImpl(
    private val userNetworkDataSource: UserNetworkDataSource,
    private val authStorageDataSource: AuthStorageDataSource
) : UserRepo {
    override suspend fun getUsers(
        pageNum: Int,
        pageSize: Int
    ): Result<List<UserEntity>> {
        val token = authStorageDataSource.token
            ?: return Result.failure(IllegalStateException("token is null"))
        return userNetworkDataSource.getUsers(pageNum, pageSize, token).map { pagingdto ->
            pagingdto.content?.mapNotNull { dto ->
                UserEntity(
                    id = dto.id ?: return@mapNotNull null,
                    name = dto.name ?: return@mapNotNull null,
                    email = dto.email ?: return@mapNotNull null,
                    photoUrl = dto.photoUrl ?: return@mapNotNull null
                )
            } ?: return Result.failure(IllegalStateException("list parse error"))
        }
    }

}