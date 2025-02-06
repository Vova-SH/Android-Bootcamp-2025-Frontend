package ru.sicampus.bootcamp2025.domain.list

import ru.sicampus.bootcamp2025.data.list.ListRepoImpl

class ListUseCase(
    private val repo: ListRepoImpl
) {
    suspend operator fun invoke(
        pageNum: Int,
        pageSize: Int,
    ) = repo.getUsers(pageNum, pageSize)
}
