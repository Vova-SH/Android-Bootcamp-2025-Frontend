package ru.sicampus.bootcamp2025.domain.list

class GetUsersUseCases (
    private val repo: UserRepo
) {
    operator suspend fun invoke(
        pageNum: Int,
        pageSize: Int
    ) = repo.getUsers(pageNum,pageSize)
}
