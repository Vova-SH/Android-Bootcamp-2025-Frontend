package ru.sicampus.bootcamp.domain.list

import ru.sicampus.bootcamp.data.list.ProfileRepoImpl

class GetAnotherProfileUseCase (
    private val repo: ProfileRepoImpl
) {
    suspend operator fun invoke() = repo.getData1()
}
