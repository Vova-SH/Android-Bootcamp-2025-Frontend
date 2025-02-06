package ru.sicampus.bootcamp2025.data.sources.network

import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.sicampus.bootcamp2025.data.dtos.CenterDto

object CenterNetworkDataSource {
    private val client = Network.client

    suspend fun getCenters(token: String): Result<List<CenterDto>> = withContext(Dispatchers.IO) {
        runCatching {
            val result = client.get("http://10.0.2.2:9000/api/centers/nearest") {
                headers {
                    append(HttpHeaders.Authorization, token)
                }
            }
            if (result.status != HttpStatusCode.OK) error("Status ${result.status}")
            result.body()
        }
    }
}