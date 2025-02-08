package ru.sicampus.bootcamp2025.data.one

import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.http.isSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.sicampus.bootcamp2025.data.Network
import ru.sicampus.bootcamp2025.domain.one.UserCenter
import java.io.IOException
import java.net.URLEncoder

class OneNetworkDataSource {
    private val client = Network.client

    suspend fun getAllUsers(): List<UserCenter> {
        val response = client.get("${Network.SERVER_ADDRESS}/api/users")
        if (!response.status.isSuccess()) {
            throw IOException("HTTP error: ${response.status}")
        }
        return response.body()
    }

    suspend fun registerUserToCenter(userId: Int, centerName: String): Boolean {
        val encodedName = withContext(Dispatchers.IO) {
            URLEncoder.encode(centerName, "UTF-8")
        }
        val response = client.get(
            "${Network.SERVER_ADDRESS}/api/users/volunteer/add/$userId/$encodedName"
        )
        return response.status.isSuccess()
    }
}