package ru.sicampus.bootcamp2025.data.auth.storage

import okhttp3.Credentials

object AuthStorageDataSource {
    var token: String?
        get() {
            return AuthTokenManagerST.getInstance().token
        }
        private set(token) {
            val tokenManager: AuthTokenManagerST = AuthTokenManagerST.getInstance()
            token?.let { tokenManager.saveToken(it) } ?: tokenManager.clear()
        }

    fun hasToken(): Boolean {
        return AuthTokenManagerST.getInstance().hasToken()
    }

    fun updateToken(login: String, password: String): String {
        val updateToken = Credentials.basic(login, password)
        token = updateToken
        return updateToken
    }

    fun clear() {
        token = null
    }
}
