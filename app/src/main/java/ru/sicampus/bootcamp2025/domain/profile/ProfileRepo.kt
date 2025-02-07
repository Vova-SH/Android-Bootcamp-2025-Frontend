package ru.sicampus.bootcamp2025.domain.profile

import ru.sicampus.bootcamp2025.data.auth.AuthStorageDataSource
import ru.sicampus.bootcamp2025.data.profile.DataDto
import ru.sicampus.bootcamp2025.data.profile.ProfileNetworkDataSource

interface ProfileRepo {
    suspend fun getMyProfileData(): Result<DataEntity>
    suspend fun changeDataByLogin(dataDto: DataDto): Result<Unit>
}