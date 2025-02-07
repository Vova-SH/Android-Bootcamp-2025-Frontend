package ru.sicampus.bootcamp2025.data.sources.network

import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.sicampus.bootcamp2025.data.dtos.CenterListPagingDto
import ru.sicampus.bootcamp2025.data.dtos.FullCenterDto

object CenterNetworkDataSource {
    private val client = Network.client

    suspend fun getCenters(
        token: String,
        pageNum: Int,
        pageSize: Int
    ): Result<CenterListPagingDto> =
        withContext(Dispatchers.IO) {
            runCatching {
                val result =
                    client.get("http://10.0.2.2:9000/api/centers/paginated?page=$pageNum&size=$pageSize") {
                        headers {
                            append(HttpHeaders.Authorization, token)
                        }
                    }
                if (result.status != HttpStatusCode.OK) error("Status ${result.status}")
                result.body()
            }
        }

    suspend fun getCenterById(token: String, centerId: Int): Result<FullCenterDto> =
        withContext(Dispatchers.IO) {
            runCatching {
                val result = client.get("http://10.0.2.2:9000/api/centers/${centerId}") {
                    headers {
                        append(HttpHeaders.Authorization, token)
                    }
                }
                if (result.status != HttpStatusCode.OK) error(("Status ${result.status}"))
                result.body()
            }
        }
}