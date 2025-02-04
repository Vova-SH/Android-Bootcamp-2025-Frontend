package ru.sicampus.bootcamp2025.data.sources.locale

import android.content.SharedPreferences
import okhttp3.Credentials

class CredentialsLocalDataSource private constructor(private val preferences: SharedPreferences){
    companion object {
        @Volatile
        private var instance: CredentialsLocalDataSource? = null

        fun getInstance(preferences: SharedPreferences) =
            instance ?: synchronized(this) {
                instance ?: CredentialsLocalDataSource(preferences).also { instance = it }
            }
    }

    private var token: String? = null

    fun updateToken(login: String, password: String): String {
        val updatedToken = Credentials.basic(login, password)
        token = updatedToken
        cacheData()
        return updatedToken
    }

    private fun cacheData() {
        with(preferences.edit()) {
            putString("token", token)
            apply()
        }
    }

    fun getToken(): String? {
        return token
    }

}