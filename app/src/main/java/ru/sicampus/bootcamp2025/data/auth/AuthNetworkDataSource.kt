package ru.sicampus.bootcamp2025.data.auth

import android.util.Log
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.sicampus.bootcamp2025.data.Network
import ru.sicampus.bootcamp2025.data.Network.SERVER_ADDRESS


object AuthNetworkDataSource {

    suspend fun isUserExist(login: String): Result<Boolean> = withContext(Dispatchers.IO) {
        runCatching {
            val response = Network.client.get("$SERVER_ADDRESS/api/username/$login")
            Log.e("INFO","response: \"$SERVER_ADDRESS/api/username/$login\"")
            response.status == HttpStatusCode.OK
        }
    }

    suspend fun login(login: String, password: String): Result<Unit> = withContext(Dispatchers.IO) {
        runCatching {
            val response = Network.client.get("$SERVER_ADDRESS/api/users/login") {
                contentType(ContentType.Application.Json)
                setBody(LoginRequestDto(login, password))
            }

            if (response.status != HttpStatusCode.OK) {
                error("Status ${response.status}: ${response.body<String>()}")
            }
            Unit
        }
    }

    suspend fun register(login: String, password: String, name: String, email: String): Result<Unit> =
        withContext(Dispatchers.IO) {
            runCatching {
                val response = Network.client.post("$SERVER_ADDRESS/api/users/register") {
                    contentType(ContentType.Application.Json)
                    setBody(
                        AuthRegisterDto(
                            username = login,
                            password = password,
                            name = name,
                            email = email
                        )
                    )
                }

                if (response.status != HttpStatusCode.Created) {
                    error("Status ${response.status}: ${response.body<String>()}")
                }
                Unit
            }
        }
}
