package ru.sicampus.bootcamp.data.centers

import android.util.Log
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.sicampus.bootcamp.data.Network

class CentersNetworkDataSource {
    suspend fun getCenters(
        token: String
    ): Result<List<CentersDto>> = withContext(Dispatchers.IO){
        runCatching {
            val result = Network.client.get("http://192.168.1.102:8080/api/1.0/organization/organizations"){
            headers {
                append(HttpHeaders.Authorization, token)
            }
            }
            if(result.status != HttpStatusCode.OK){
                error("Status ${result.status}")
            }
            Log.d("result.status", "${result}")
            result.body()
        }

    }
}
