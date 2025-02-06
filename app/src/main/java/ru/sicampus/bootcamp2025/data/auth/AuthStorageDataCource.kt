package ru.sicampus.bootcamp2025.data.auth

import okhttp3.Credentials

object AuthStorageDataCource {
    var token : String? = null
        private set

    fun updateToken(login: String, password: String): String{
        val updateToken = Credentials.basic(login, password)
        token = updateToken
        return updateToken
    }

    fun clear(){
        token = null
    }
}
