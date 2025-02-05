package ru.sicampus.bootcamp2025.domain.list

interface ListRepo {
    suspend fun getUsers(): Result<List<ListEntity>>
}
