package ru.sicampus.bootcamp2025.data.login

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
import ru.sicampus.bootcamp2025.data.Network.client
import ru.sicampus.bootcamp2025.data.Network.serverAdress
import ru.sicampus.bootcamp2025.domain.list.ListEntity

class ListNetworkDataSource {

    suspend fun getUsers() : Result<List<ListEntity>> = withContext(Dispatchers.IO) {
        runCatching {
            val result = client.get("$serverAdress/api/ve")
            if(result.status != HttpStatusCode.OK) {
                error("Status ${result.status}")
            }
            result.body()
        }
    }
}