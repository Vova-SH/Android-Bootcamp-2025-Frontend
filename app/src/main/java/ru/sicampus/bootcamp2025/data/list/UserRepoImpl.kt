package ru.sicampus.bootcamp2025.data.list

import ru.sicampus.bootcamp2025.data.auth.AuthStorageDataCource
import ru.sicampus.bootcamp2025.domain.UserEntity
import ru.sicampus.bootcamp2025.domain.UserRepo

class UserRepoImpl(
    private val userNetworkDataSource: UserNetworkDataSource,
    private val authStorageDataSource: AuthStorageDataCource
): UserRepo {
    override suspend fun getUsers(pageNumb: Int, pageSize: Int,): Result<List<UserEntity>> {
        val token = authStorageDataSource.token?: return Result.failure(IllegalStateException("token is null"))
        return userNetworkDataSource.getUsers(
            pageNumb = pageNumb,
            pageSize = pageSize,
            token = token
        ).map { pagingDto ->
            pagingDto.content?.mapNotNull{ dto ->
                UserEntity(
                    id = dto.id ?: return@mapNotNull null,
                    name = dto.name ?: return@mapNotNull null,
                    email = dto.email ?: return@mapNotNull null,
                    photoUrl = dto.photoUrl ?: return@mapNotNull null,
                )} ?: return Result.failure(IllegalStateException("List parse error"))
        }

    }
}



