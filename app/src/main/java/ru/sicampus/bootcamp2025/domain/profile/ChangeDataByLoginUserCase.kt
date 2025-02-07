package ru.sicampus.bootcamp2025.domain.profile

import ru.sicampus.bootcamp2025.data.profile.DataDto

class ChangeDataByLoginUserCase(
    private val profileRepo: ProfileRepo,
) {
    operator suspend fun invoke(dataEntity: DataDto): Result<Unit> {
        return profileRepo.changeDataByLogin(dataEntity)
    }
}