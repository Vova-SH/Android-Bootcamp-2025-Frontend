package ru.sicampus.bootcamp2025.data.auth

import okhttp3.Credentials
import ru.sicampus.bootcamp2025.data.save.PrefsKeys
import ru.sicampus.bootcamp2025.data.save.PrefsManager


object AuthStorageDataSource {
    private var token: String? = null
        get() = field ?: PrefsManager.getInstance().getString(PrefsKeys.AUTH_TOKEN)

    fun updateToken(login: String, password: String): String {
        return Credentials.basic(login, password).also {
            token = it
            PrefsManager.getInstance().putString(PrefsKeys.AUTH_TOKEN, it)
        }
    }

    fun getCurrentToken(): String? = token

    fun clear() {
        token = null
        PrefsManager.getInstance().apply {
            remove(PrefsKeys.AUTH_TOKEN)
            putBoolean(PrefsKeys.IS_LOGGED_IN, false)
        }
    }

    fun isLoggedIn(): Boolean {
        return PrefsManager.getInstance().getBoolean(PrefsKeys.IS_LOGGED_IN, false)
                && getCurrentToken() != null
    }

    fun initialize() {
        token = PrefsManager.getInstance().getString(PrefsKeys.AUTH_TOKEN)
    }
}