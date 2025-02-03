package ru.sicampus.bootcamp2025.data

import android.util.Log
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

class CenterNetworkDataSource {

    private  val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json(Json {
                isLenient = true
                ignoreUnknownKeys = true
            })
        }
    }
    suspend fun getCenters(): Result<List<CenterDto>> = withContext(Dispatchers.IO) {
        runCatching {
            Log.d("Zapros", "начал отправлять запросы")

            //val user_cord : String = "ща реализую получение местоположения и сюда закину координаты"
            //val user_cord2 : String = "ща реализую получение местоположения и сюда закину координаты"
            //val result = client.get("https://localhost:8081/api/center/closest?${user_cord}&${user_cord2}")
            val result = client.get("http://localhost:8081/api/center")

            Log.d("serverCode", "лялялял")
            Log.d("serverCode", "${result.status}")
            if (result.status != HttpStatusCode.OK) {
                error("Status ${result.status}")
            }
            result.body()
        }
    }
}