package ru.sicampus.bootcamp2025.data

import ru.sicampus.bootcamp2025.domain.VolunteerEntity
import ru.sicampus.bootcamp2025.domain.VolunteerRepo

class VolunteerRepoImpl(private val volunteerNetworkDataSource: VolunteerNetworkDataSource):VolunteerRepo {
    override suspend fun getUsers(): Result<List<VolunteerEntity>> {
        return volunteerNetworkDataSource.getUsers().map { listDto ->
            listDto.mapNotNull { dto ->
                VolunteerEntity(
                    name = dto.name ?: return@mapNotNull null,
                    email = dto.email ?: return@mapNotNull null,
//                    photoUrl = dto.photoUrl ?: return@mapNotNull null
                )
            }
        }

    }


}