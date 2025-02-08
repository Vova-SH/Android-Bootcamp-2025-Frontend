package ru.sicampus.bootcamp2025.data.user

import ru.sicampus.bootcamp2025.domain.user.UserEntity
import ru.sicampus.bootcamp2025.domain.user.UserRepo

class UserRepoImpl (
    private val userNetworkDataSource: UserNetworkDataSource
) : UserRepo {
    override suspend fun getUser(id: Long?): Result<UserEntity> {
        return userNetworkDataSource.getUser(id).map { dto ->
            UserEntity(
                id = dto.id ?: -1,
                email = dto.email,
                birthDate = dto.birthDate ?: "",
                name = dto.name,
                description = dto.description ?: "",
                avatarUrl = dto.avatarUrl ?: "",
                center = dto.center ?: "",
                authorities = dto.authorities,
                centerDescription = dto.centerDescription ?: "",
                createdAt = dto.createdAt,
                joinedAt = dto.joinedAt
                )

            }
        }
}