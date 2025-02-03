package ru.sicampus.bootcamp2025.data.sources.network

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.http.HttpStatusCode
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import ru.sicampus.bootcamp2025.data.dtos.CenterDto

class CenterNetworkDataSource {
    private val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json(Json {
                isLenient = true
                ignoreUnknownKeys = true
            })
        }
    }

    suspend fun getCenters(): Result<List<CenterDto>> = withContext(Dispatchers.IO) {
        runCatching {
            val result = client.get("http://10.0.2.2:9000/api/centers/nearest")
            if (result.status != HttpStatusCode.OK) error("Status ${result.status}")
            result.body()
        }
    }
}