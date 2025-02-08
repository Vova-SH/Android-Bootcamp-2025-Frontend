package ru.sicampus.bootcamp2025.data.auth

import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.sicampus.bootcamp2025.data.Network.client
import ru.sicampus.bootcamp2025.data.user.UserDto

object AuthNetworkDataSource {


    suspend fun isUserExist(email: String): Result<Boolean?> = withContext(Dispatchers.IO) {
        runCatching {
            val result = client.get("http://10.0.2.2:8081/api/user/email/$email") //10.0.2.2
            when (result.status) {
                HttpStatusCode.OK -> { return@runCatching true }
                HttpStatusCode.NotFound -> { return@runCatching false }
                else -> {return@runCatching null }
            }
        }
    }

    suspend fun login(token: String): Result<UserDto> = withContext(Dispatchers.IO) {
        runCatching {
            val result = client.get("http://10.0.2.2:8081/api/user/login") {
                header(HttpHeaders.Authorization, token)
            }
            if (result.status == HttpStatusCode.Unauthorized) {
                error("Неверный email или пароль")
            }
            result.body<UserDto>()
        }

    }

    suspend fun register(email: String, password: String, name: String): Result<UserDto> =
        withContext(Dispatchers.IO) {
            runCatching {
                val result = client.post("http://10.0.2.2:8081/api/user/register") {
                    contentType(ContentType.Application.Json)
                    setBody(
                        AuthRegisterDto(
                            email = email,
                            password = password,
                            name = name
                        )

                    )
                }
                if (result.status != HttpStatusCode.Created) {
                    error("Непредвиденная ошибка")
                }
                result.body<UserDto>()
            }
    }
}