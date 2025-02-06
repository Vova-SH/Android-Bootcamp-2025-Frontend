package ru.sicampus.bootcamp2025.domain

interface UserRepo
{
    suspend fun getUsers(
        pageNumb: Int,
        pageSize: Int,
    ): Result<List<UserEntity>>

}
