package ru.sicampus.bootcamp2025.data.list

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import okhttp3.Credentials
import ru.sicampus.bootcamp2025.data.auth.AuthStorageDataSource

class UserNetworkDataSource(
    private val authStorageDataSource: AuthStorageDataSource
) {
    private val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json(Json {
                isLenient = true
                ignoreUnknownKeys = true
            })
        }
    }

    suspend fun getUsers(
        pageNum: Int,
        pageSize: Int,
    ): Result<ListPagingDto> = withContext(Dispatchers.IO) {
        runCatching {
            val credentials = authStorageDataSource.getCredentials()
                ?: throw IllegalStateException("User not authenticated")

            val result = client.get("http://10.0.2.2:9000/api/person/paginated?page=$pageNum&size=$pageSize") {
                headers {
                    append(
                        HttpHeaders.Authorization,
                        Credentials.basic(credentials.first, credentials.second)
                    )
                }
            }

            if (result.status != HttpStatusCode.OK) {
                error("Status ${result.status}")
            }
            result.body()
        }
    }
}