package ru.sicampus.bootcamp2025.domain.profile

class GetDataByLoginUserCase(
    private val profileRepo: ProfileRepo
) {
    operator suspend fun invoke(): Result<PersonEntity> {
        return profileRepo.getMyProfileData()
    }
}