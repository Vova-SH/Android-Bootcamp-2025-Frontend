package ru.sicampus.bootcamp2025.data.auth

import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.append
import io.ktor.http.contentType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.sicampus.bootcamp2025.data.Network
import kotlin.math.log

object AuthNetworkDataSource {
    suspend fun isUserExist (login: String): Result<Boolean> = withContext(Dispatchers.IO){
        runCatching {
            val result = Network.client.get("https://10.0.2.2:9000/api/persons/username/${login}")
            result.status == HttpStatusCode.OK
        }

    }
    suspend fun login(token: String): Result<Unit> = withContext(Dispatchers.IO){
        runCatching {
            val result = Network.client.get("https://10.0.2.2:9000/api/persons/login"){
                    headers {
                        append(HttpHeaders.Authorization, token)
                    }
            }
            if(result.status != HttpStatusCode.OK){
                error("Status ${result.status}")
            }
            Unit
        }
    }

    suspend fun register(login: String, password: String): Result<Unit> = withContext(Dispatchers.IO){
        runCatching {
            val result = Network.client.get("https://10.0.2.2:9000/api/persons/register"){
                contentType(ContentType.Application.Json)
                setBody(
                    AuthRegistrerDto(
                    username = login,
                    password = password
                    )
                )
            }
            if(result.status != HttpStatusCode.Created){
                error("Status ${result.status}")
            }
            Unit
        }
    }
}
