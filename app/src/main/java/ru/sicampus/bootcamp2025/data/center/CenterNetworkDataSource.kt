package ru.sicampus.bootcamp2025.data.center

import android.util.Log
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.sicampus.bootcamp2025.data.Network.client
import ru.sicampus.bootcamp2025.data.auth.AuthStorageDataSource.token

class CenterNetworkDataSource {
    suspend fun getCenters(): Result<List<CenterDto>> = withContext(Dispatchers.IO) {
        runCatching {
            Log.d("Zapros", "начал отправлять запросы")
            //val result = client.get("https://localhost:8081/api/center/closest?${user_cord}&${user_cord2}")
            val result = client.get("http://10.0.2.2:8081/api/center")  {
                header(HttpHeaders.Authorization, token)
            }


            Log.d("serverCode", "${result.status}")
            if (result.status != HttpStatusCode.OK) {
                error("Status ${result.status}")
            }
            Log.d("result", result.bodyAsText())
            result.body()
        }
    }
}