package ru.sicampus.bootcamp2025.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import ru.sicampus.bootcamp2025.domain.UserRepo

class UserRepoImpl:UserRepo {
    override suspend fun fetchUser(): UserDTO {
        return withContext(Dispatchers.IO) {
            val userDeferred = async {
                delay(1000L)
                UserDTO(
                    name = "Ivan",
                    lastName = "Ivanov",
                    age = 30
                )
            }
            userDeferred.await()
        }
    }
}

