package ru.sicampus.bootcamp2025.domain.profile

import io.ktor.util.reflect.instanceOf
import ru.sicampus.bootcamp2025.data.profile.DataDto

class GetDataByLoginUserCase(
    private val profileRepo: ProfileRepo
) {
    operator suspend fun invoke(): Result<DataEntity> {
        return profileRepo.getMyProfileData()
    }
}