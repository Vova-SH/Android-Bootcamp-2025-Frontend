package ru.sicampus.bootcamp2025.data.auth

import android.util.Log
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType
import io.ktor.http.encodeOAuth
import io.ktor.http.encodeURLPath
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.sicampus.bootcamp2025.data.Constants.serverIp
import ru.sicampus.bootcamp2025.data.Network

object AuthNetworkDataSource {
    suspend fun isUserExist(login: String): Result<Boolean> = withContext(Dispatchers.IO) {
        runCatching {
            val encodedLogin = login.encodeURLPath()
            val result = Network.client.get("$serverIp/api/person/username/$encodedLogin")
            result.status != HttpStatusCode.OK
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
                error("Status ${result.status}: ${result.body<String>()}")
            }
            Unit
        }
    }

    suspend fun register(login: String, password: String): Result<Unit> =
        withContext(Dispatchers.IO) {
            runCatching {
                val result = Network.client.post("$serverIp/api/person/register") {
                    contentType(ContentType.Application.Json)
                    setBody(
                        AuthRegisterDto(
                            username = login,
                            password = password,
                            name = login,
                            email = "$login@example.com"
                        )
                    )
                }
                if (result.status != HttpStatusCode.Created) {
                    val responseBody = result.bodyAsText()
                    Log.w("bbb", "Status: ${result.status}, Body: ${result.body<String>()}")
                    error("Status ${result.status}: ${result.body<String>()}")
                }
                Unit
            }
        }
}