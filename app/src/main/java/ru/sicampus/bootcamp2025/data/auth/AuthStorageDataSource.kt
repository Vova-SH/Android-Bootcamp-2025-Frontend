package ru.sicampus.bootcamp2025.data.auth

import okhttp3.Credentials
import ru.sicampus.bootcamp2025.data.user.UserDto


object AuthStorageDataSource {
    var token: String? = null
        private set

    var userInfo : UserDto? = null

    fun updateToken(email : String, password : String) : String {
        val updateToken = Credentials.basic(email, password)
        token = updateToken
        return updateToken
    }
    fun updateUserInfo(userDto: Result<UserDto>) {
        userDto.onSuccess { user ->
            userInfo = user
        }.onFailure { error ->
            userInfo = null
            error("Server Error id = null")
        }

    }

    fun clear() {
        token = null
    }
}