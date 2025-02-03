package ru.sicampus.bootcamp2025.domain.profile

import ru.sicampus.bootcamp2025.domain.UserEntity

class GetProfileUseCase(
    private val repo : ProfileRepo
) {
    suspend operator fun invoke() = repo.getProfile()
    }
