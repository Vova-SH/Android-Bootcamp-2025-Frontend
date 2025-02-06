package ru.sicampus.bootcamp2025.domain

class GetSerUseCase(private val repo: UserRepo,
) {
    operator suspend fun invoke(
        pageNumb: Int,
        pageSize: Int,
    ) = repo.getUsers(pageNumb, pageSize)
}
