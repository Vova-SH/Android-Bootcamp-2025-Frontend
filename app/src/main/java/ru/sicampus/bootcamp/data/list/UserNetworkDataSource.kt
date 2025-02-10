package ru.sicampus.bootcamp.data.list

import android.util.Log
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.sicampus.bootcamp.data.Network
import ru.sicampus.bootcamp.domain.list.ProfileEntity
import kotlin.math.log

class UserNetworkDataSource {

    suspend fun getUsers(
        token: String
    ): Result<List<UserDto>> = withContext(Dispatchers.IO) {
        runCatching {
            val result = Network.client.get("http://192.168.1.102:8080/api/1.0/user/free") {
                headers {
                    append(HttpHeaders.Authorization, token)
                }

            }
            if (result.status != HttpStatusCode.OK) {
                error("Status ${result.status}")
            }
            Log.d("result.status","${result}")
            result.body()
        }
    }
    suspend fun getUserByLogin(
        token: String,
        login: String,
    ): Result<ProfileDto> = withContext(Dispatchers.IO){
        runCatching {
            val result = Network.client.get("http://192.168.1.102:8080/api/1.0/user/username/${login}") {
                headers {
                    append(HttpHeaders.Authorization, token)
                }

            }
            if (result.status != HttpStatusCode.OK) {
                error("Status ${result.status}")
            }
            Log.d("result.status","${result}")
            result.body()
        }
    }
}
