package ru.sicampus.bootcamp2025.data.map

import android.util.Log
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.sicampus.bootcamp2025.data.Constants.serverIp
import ru.sicampus.bootcamp2025.data.Network
import io.ktor.client.request.headers
import io.ktor.client.request.patch
import io.ktor.client.request.setBody
import io.ktor.http.encodeURLPath
import ru.sicampus.bootcamp2025.data.profile.PersonDto
import ru.sicampus.bootcamp2025.data.profile.PersonUpdateDto

class MapNetworkDataSource() {
    suspend fun getPlaces(token: String): Result<DepartmentListDto> = withContext(Dispatchers.IO) {
        runCatching {
            Log.d("MapNetworkDataSource", "$token")
            val result = Network.client.get("${serverIp}/api/department/paginated") {
                headers {
                    append(HttpHeaders.Authorization, token)
                }
            }
            if (result.status != HttpStatusCode.OK) {
                Log.e("MapNetworkDataSource", result.status.toString())
                error("Status ${result.status}: ${result.body<String>()}")
            }
            result.body()
        }
    }
    suspend fun changeDepartmentName(personUpdateDto: PersonUpdateDto, token: String): Result<Unit> = withContext(Dispatchers.IO){
        runCatching {
            val result = Network.client.patch("${serverIp}/api/person/me") {
                headers {
                    append(HttpHeaders.Authorization, token)
                    append(HttpHeaders.ContentType, "application/json")
                }
                setBody(personUpdateDto)
            }
            result.body()
        }
    }

    suspend fun getPlaceByName(name: String, token: String): Result<DepartmentDto> = withContext(Dispatchers.IO) {
        runCatching {
            Log.d("zzz", "test11")
            Log.d("zzz", "$name")
            val encodedName = name.encodeURLPath()
            val result = Network.client.get("${serverIp}/api/department/name/$encodedName") {
                headers{
                    append(HttpHeaders.Authorization, token)
                }
            }
            Log.d("zzz",  "good ${result.status}: ${result.body<String>()}")
            if (result.status != HttpStatusCode.OK) {
                Log.d("zzz",  "${result.status}: ${result.body<String>()}")
                error("Status ${result.status}: ${result.body<String>()}")
            }
            result.body()
        }
    }
}
