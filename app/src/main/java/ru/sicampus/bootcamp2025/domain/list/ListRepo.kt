package ru.sicampus.bootcamp2025.domain.list

interface ListRepo {
    suspend fun getUsers(
        pageNum: Int,
        pageSize: Int,
    ): Result<List<ListEntity>>
}
