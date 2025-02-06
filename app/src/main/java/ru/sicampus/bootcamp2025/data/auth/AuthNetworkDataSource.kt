package ru.sicampus.bootcamp2025.data.auth

import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.sicampus.bootcamp2025.data.Constants.serverIp
import ru.sicampus.bootcamp2025.data.Network
import kotlin.math.log

object AuthNetworkDataSource {
    suspend fun isUserExist(login:String): Result<Boolean> = withContext(Dispatchers.IO) {
        runCatching {
            val result = Network.client.get("$serverIp/api/person/$login")
            result.status == HttpStatusCode.OK
        }
    }

    suspend fun login(token: String): Result<Unit> = withContext(Dispatchers.IO) {
        runCatching {
            val result = Network.client.get("$serverIp/api/person/login") {
                headers {
                    append(HttpHeaders.Authorization, token)
                }
            }
            if (result.status != HttpStatusCode.OK) {
            error("Status ${result.status}")
            }
            Unit
        }
    }
    suspend fun register(login: String, password: String): Result<Unit> = withContext(Dispatchers.IO) {
        runCatching {
            val result = Network.client.post("$serverIp/api/person/register") {
                contentType(ContentType.Application.Json)
                setBody(AuthRegisterDto(
                    username = login,
                    password = password
                ))
            }
            if (result.status != HttpStatusCode.Created) {
                error("Status ${result.status}")
            }
            Unit
        }
    }
}