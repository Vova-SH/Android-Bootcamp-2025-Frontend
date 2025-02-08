package ru.sicampus.bootcamp2025.data.list

import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.sicampus.bootcamp2025.data.Constants.serverIp
import ru.sicampus.bootcamp2025.data.Network

class UserNetworkDataSource {
    suspend fun getUsers(
        pageNum: Int,
        pageSize: Int,
        token: String
    ): Result<UserListPagingDto> = withContext(Dispatchers.IO) {
        runCatching {
            val result = Network.client.get("$serverIp/api/person/paginated?page=$pageNum&size=$pageSize") {
                headers {
                    append(HttpHeaders.Authorization, token)
                }
            }
            if (result.status != HttpStatusCode.OK) {
                error("Status ${result.status}")
            }
            result.body()
        }
    }
}