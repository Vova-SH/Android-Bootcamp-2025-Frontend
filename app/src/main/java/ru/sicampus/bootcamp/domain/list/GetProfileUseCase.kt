package ru.sicampus.bootcamp.domain.list

import ru.sicampus.bootcamp.data.list.ProfileRepoImpl
import ru.sicampus.bootcamp.domain.auth.ProfileRepo

class GetProfileUseCase (
private val repo: ProfileRepoImpl
) {
    suspend operator fun invoke() = repo.getData()
}
