package ru.sicampus.bootcamp2025.data.list

import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.http.HttpStatusCode
import io.ktor.serialization.kotlinx.json.json
import  kotlinx.serialization.json.Json
import ru.sicampus.bootcamp2025.domain.UserEntity

class UserNetworkDataSource() {
    private val client = HttpClient(CIO){
        install(ContentNegotiation){
            json(Json{
                isLenient = true
                ignoreUnknownKeys = true
            })
        }
    }
    suspend fun getUsers(): Result<List<UserEntity>>{
        val result = client.get("https://10.0.2.2:9000/api/persons")
        if(result.status != HttpStatusCode.OK){
            error("Status ${result.status}")
        }
        TODO()
    }
}
