package ru.sicampus.bootcamp.domain.Centers

import ru.sicampus.bootcamp.data.list.CentersRepoImpl
import ru.sicampus.bootcamp.domain.auth.AuthRepo

class CentersUserUseCase (
    private val centersRepoImpl: CentersRepoImpl,
) {

    suspend operator fun invoke() = centersRepoImpl.getCenters()

}

