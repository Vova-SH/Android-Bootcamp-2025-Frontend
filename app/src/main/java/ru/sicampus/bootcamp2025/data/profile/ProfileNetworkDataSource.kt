package ru.sicampus.bootcamp2025.data.profile

import android.util.Log
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.client.request.patch
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.encodeURLPath
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.sicampus.bootcamp2025.data.Constants.serverIp
import ru.sicampus.bootcamp2025.data.Network
import ru.sicampus.bootcamp2025.domain.profile.DataEntity


class ProfileNetworkDataSource {

    suspend fun getMyProfileData(token: String): Result<DataDto> = withContext(Dispatchers.IO){
        runCatching {
            val result = Network.client.get("$serverIp/api/person/me") {//TODO() api path
                headers {
                    append(HttpHeaders.Authorization, token)
                }
            }
            Log.d("ProfileNetworkDataSource", result.bodyAsText())

            if (result.status != HttpStatusCode.OK){
                Log.e("ProfileNetworkDataSource", result.bodyAsText())
            }
            result.body()
        }
    }
    suspend fun changeDataByLogin(token: String, dataDto: DataDto): Result<Unit> = withContext(Dispatchers.IO) {
        runCatching {
            val result = Network.client.patch("$serverIp/api/person/me") {
                headers {
                    append(HttpHeaders.Authorization, token)
                    append(HttpHeaders.ContentType, "application/json")
                }
                setBody(dataDto)
            }
            Log.d("ProfileNetworkDataSource", result.bodyAsText())

            if (result.status != HttpStatusCode.OK){
                Log.e("ProfileNetworkDataSource", result.bodyAsText())
            }
            result.body()
        }
    }
}