package ru.sicampus.bootcamp2025.data.profile

import ru.sicampus.bootcamp2025.data.UserDTO
import ru.sicampus.bootcamp2025.domain.profile.ProfileRepo


class ProfileRepoImpl(
    private val profileNetworkDataSource: ProfileNetworkDataSource
) : ProfileRepo{
    override suspend fun getProfile(): Result<UserDTO> {
        return  profileNetworkDataSource.getProfile().map{
            dto ->
            UserDTO(
                name = dto.name,
                photoUrl = dto.photoUrl,
                id = dto.id ,
                email = dto.email,
                role = dto.role,
                birthDate = dto.birthDate,
                phoneNumber = dto.phoneNumber,
                telegramUsername = dto.telegramUsername,
                organizationName = dto.organizationName,
                about = dto.about
            )
        }
    }
}