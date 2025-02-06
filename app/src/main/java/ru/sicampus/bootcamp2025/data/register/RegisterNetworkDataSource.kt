package ru.sicampus.bootcamp2025.data.register

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import kotlinx.serialization.encodeToString
import ru.sicampus.bootcamp2025.data.UserDTO
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

class RegisterNetworkDataSource {
    private val client = HttpClient(CIO){
        install(ContentNegotiation){
            json(Json {
                isLenient = true
                ignoreUnknownKeys = true
            })
        }
    }
    @OptIn(ExperimentalEncodingApi::class)

    suspend fun register(registerDTO: UserRegisterDTO): Result<UserDTO> = withContext(Dispatchers.IO) {
        val jsonString = Json.encodeToString(registerDTO)
        println("Sending JSON: $jsonString")
        runCatching {
            println("Making network request to register: $registerDTO")
            val result = client.post("http://10.0.2.2:8080/api/volunteers/register") {
                headers {
                    contentType(ContentType.Application.Json)
                }
                setBody(jsonString) // Сериализация данных
            }

            if (result.status != HttpStatusCode.OK) {
                println("Error response: ${result.status}")
                error("Status ${result.status}")
            }
            println("Registration successful")
            result.body<UserDTO>()
        }
    }
}