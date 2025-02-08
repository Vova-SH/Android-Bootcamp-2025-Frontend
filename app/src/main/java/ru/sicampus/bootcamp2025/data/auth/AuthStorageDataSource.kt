package ru.sicampus.bootcamp2025.data.auth

import ru.sicampus.bootcamp2025.data.save.PrefsKeys
import ru.sicampus.bootcamp2025.data.save.PrefsManager

class AuthStorageDataSource {

    fun isLoggedIn(): Boolean {
        return PrefsManager.getInstance().getBoolean(PrefsKeys.IS_LOGGED_IN, false)
    }

    fun saveCredentials(login: String, password: String) {
        PrefsManager.getInstance().putString(PrefsKeys.LOGIN, login)
        PrefsManager.getInstance().putString(PrefsKeys.PASSWORD, password)
    }

    fun saveUserDetails(name: String, email: String) {
        PrefsManager.getInstance().putString(PrefsKeys.NAME, name)
        PrefsManager.getInstance().putString(PrefsKeys.EMAIL, email)
    }

    fun getCredentials(): Pair<String, String> {
        val login = PrefsManager.getInstance().getString(PrefsKeys.LOGIN)
        val password = PrefsManager.getInstance().getString(PrefsKeys.PASSWORD)
        return Pair(login, password)
    }

    fun getName(): String = PrefsManager.getInstance().getString(PrefsKeys.NAME)
    fun getEmail(): String = PrefsManager.getInstance().getString(PrefsKeys.EMAIL)

    fun clear() {
        PrefsManager.getInstance().apply {
            remove(PrefsKeys.IS_LOGGED_IN)
            remove(PrefsKeys.LOGIN)
            remove(PrefsKeys.PASSWORD)
            remove(PrefsKeys.NAME)
            remove(PrefsKeys.EMAIL)
        }
    }
}