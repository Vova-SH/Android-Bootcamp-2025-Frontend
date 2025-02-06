package ru.sicampus.bootcamp2025.data.list

import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpHeaders.Authorization
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.Credentials
import ru.sicampus.bootcamp2025.data.Network
import ru.sicampus.bootcamp2025.domain.UserEntity

class UserNetworkDataSource() {

    suspend fun getUsers(
        pageNumb: Int,
        pageSize: Int,
        token: String
    ): Result<UserListPagingDto> = withContext(Dispatchers.IO){
        runCatching {
            val result = Network.client.get("http://10.0.2.2:9000/api/persons/paginated?page=$pageNumb&size=$pageSize"){
                headers {
                    append(
                        HttpHeaders.Authorization,
                        Credentials.basic("anepretimov", "admin")
                    )
                }
            }
            if(result.status != HttpStatusCode.OK){
                error("Status ${result.status}")
            }
            result.body()
        }
    }
}
