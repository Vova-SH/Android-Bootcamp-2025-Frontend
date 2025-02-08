package ru.sicampus.bootcamp2025.domain.profile

import ru.sicampus.bootcamp2025.data.profile.PersonDto

class ChangeDataByLoginUserCase(
    private val profileRepo: ProfileRepo,
) {
    operator suspend fun invoke(personDto: PersonDto): Result<Unit> {
        return profileRepo.changeDataByLogin(personDto)
    }
}