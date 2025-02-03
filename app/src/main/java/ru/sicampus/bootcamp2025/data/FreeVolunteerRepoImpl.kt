package ru.sicampus.bootcamp2025.data

import ru.sicampus.bootcamp2025.domain.FreeVolunteerRepo
import ru.sicampus.bootcamp2025.domain.UserEntity

class FreeVolunteerRepoImpl(
    private val freeVolunteerNetworkDataSource: FreeVolunteerNetworkDataSource
) : FreeVolunteerRepo{
    override suspend fun getFreeVolunteers(): Result<List<UserEntity>> {
        return  freeVolunteerNetworkDataSource.getFreeVolunteers().map { listDto ->
            println("Dto list: $listDto")
            listDto.mapNotNull { dto ->
                UserEntity(
                    firstName = dto.firstName,
                    lastName = dto.lastName,
                    photoUrl = dto.photoUrl ?: return@mapNotNull null,
                    id = dto.id ,
                    email = dto.email,
                    role = dto.role,
                    birthDate = dto.birthDate ?: return@mapNotNull null,
                    phoneNumber = dto.phoneNumber ?: return@mapNotNull null,
                    telegramUsername = dto.telegramUsername ?: return@mapNotNull null,
                    organizationName = dto.organizationName,
                    about = dto.about ?: return@mapNotNull null,
                )

            }

        }
    }
}