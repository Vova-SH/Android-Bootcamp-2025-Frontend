package ru.sicampus.bootcamp2025.data.sources.network

import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.sicampus.bootcamp2025.Const
import ru.sicampus.bootcamp2025.data.dtos.ProfileDto

object ProfileNetworkDataSource {
    private val client = Network.client

    suspend fun getProfileById(profileId: Int, token: String): Result<ProfileDto> = withContext(Dispatchers.IO) {
        runCatching {
            val result = client.get("${Const.DOMAIN}/api/profile/${profileId}") {
                headers {
                    append(HttpHeaders.Authorization, token)
                }
            }
            if (result.status != HttpStatusCode.OK) error("Status ${result.status}")
            result.body()
        }
    }
}