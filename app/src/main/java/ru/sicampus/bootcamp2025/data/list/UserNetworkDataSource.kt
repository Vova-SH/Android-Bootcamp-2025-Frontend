package ru.sicampus.bootcamp2025.data.list

import io.ktor.client.request.get
import io.ktor.http.HttpStatusCode
import ru.sicampus.bootcamp2025.data.Network
import ru.sicampus.bootcamp2025.domain.UserEntity

class UserNetworkDataSource() {

    suspend fun getUsers(): Result<List<UserEntity>>{
        val result = Network.client.get("https://10.0.2.2:9000/api/persons")
        if(result.status != HttpStatusCode.OK){
            error("Status ${result.status}")
        }
        TODO()
    }
}
