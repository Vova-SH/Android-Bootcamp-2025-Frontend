package ru.sicampus.bootcamp2025.data

import ru.sicampus.bootcamp2025.domain.UserEntity
import ru.sicampus.bootcamp2025.domain.UserRepo

class UserRepoImpl(private val userNetworkDataSource: UserNetworkDataSource): UserRepo {
    override suspend fun getUsers(): Result<List<UserEntity>> {
        return userNetworkDataSource.getUsers().map {
            listDto -> listDto.mapNotNull{ dto ->

                UserEntity(
                    name = dto.name ?: return@mapNotNull null,
                    email = dto.email ?: return@mapNotNull null,
                    photoUrl = dto.photoUrl ?: return@mapNotNull null,
                )
            }

        }
    }
}

