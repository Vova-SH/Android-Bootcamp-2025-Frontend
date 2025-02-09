package ru.sicampus.bootcamp.data.auth

import io.ktor.client.call.body
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
import ru.sicampus.bootcamp.data.Network

object AuthNetworkDataSource {


    suspend fun login(token: String): Result<Unit> = withContext(Dispatchers.IO) {
        runCatching {
            val result = Network.client.get("http://192.168.1.102:8080/api/1.0/login") {
                headers {
                    append(HttpHeaders.Authorization, token)
                }
            }
            if (result.status != HttpStatusCode.OK) {
                error("Status ${result.status}: ${result.body<String>()}")
            }
            Unit
        }
    }

    suspend fun register(login: String, password: String, email: String, name: String, secondName: String, lastName: String, organizationName: String, phoneNumber: String, info: String): Result<Unit> =
        withContext(Dispatchers.IO) {
            runCatching {
                val result = Network.client.post("http://192.168.1.102:8080/api/1.0/register") {
                    contentType(ContentType.Application.Json)
                    setBody(
                        AuthRegisterDto(
                            username = login,
                            password = password,
                            name = name,
                            secondName = secondName,
                            lastName = lastName,
                            email = email,
                            organizationName = organizationName,
                            info = info,
                            phoneNumber = phoneNumber,
                        )
                    )
                }
                if (result.status != HttpStatusCode.Created) {
                    error("Status ${result.status}: ${result.body<String>()}")
                }
                Unit
            }

        }
    suspend fun findByLogin(token: String, login: String): Result<Unit> = withContext(Dispatchers.IO) {
        runCatching {
            val result = Network.client.get("http://192.168.1.102:8080/api/1.0/user/username/${login}") {
                headers {
                    append(HttpHeaders.Authorization, token)
                }
            }
            if (result.status != HttpStatusCode.OK) {
                error("Status ${result.status}: ${result.body<String>()}")
            }
            Unit
        }
    }
}
