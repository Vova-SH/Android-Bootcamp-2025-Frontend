package ru.sicampus.bootcamp2025.data

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.http.HttpStatusCode
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

class FreeVolunteerNetworkDataSource {
    private val client = HttpClient(CIO){
        install(ContentNegotiation){
            json(Json {
                isLenient = true
                ignoreUnknownKeys = true
            })
        }
    }

    @OptIn(ExperimentalEncodingApi::class)
    suspend fun getFreeVolunteers():Result<List<UserDTO>> = withContext(Dispatchers.IO){
        runCatching {
            val result = client.get("http://10.0.2.2:8080/api/volunteers/free"){
                headers{
                    append("Authorization", "Basic ${ Base64.encode("i@indexzero.su:HelloWorld1234".encodeToByteArray())}") // Тут будет строчка с данными пользователя
                }
            }
            println("Raw JSON: ${result.body<String>()}") // Логирование
            if (result.status != HttpStatusCode.OK) {
                error("Status ${result.status}")
            }
            result.body()

        }

    }
}