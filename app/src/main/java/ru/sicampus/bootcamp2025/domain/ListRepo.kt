package ru.sicampus.bootcamp2025.domain

interface ListRepo {
    suspend fun getUsers(): Result<List<ListEntity>>
}
