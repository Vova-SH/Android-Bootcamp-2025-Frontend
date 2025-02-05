package ru.sicampus.bootcamp2025.data.sources.network

import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.sicampus.bootcamp2025.data.dtos.ProfileDto

object ProfileNetworkDataSource {
    private val client = Network.client

    suspend fun getProfileById(profileId: Int): Result<ProfileDto> = withContext(Dispatchers.IO) {
        runCatching {
            val result = client.get("http://10.0.2.2:9000/api/profile/${profileId}")
            if (result.status != HttpStatusCode.OK) error("Status ${result.status}")
            result.body()
        }
    }
}