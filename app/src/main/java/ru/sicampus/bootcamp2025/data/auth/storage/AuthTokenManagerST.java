package ru.sicampus.bootcamp2025.data.auth.storage;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.Nullable;
import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKeys;

import java.io.IOException;
import java.security.GeneralSecurityException;

public class AuthTokenManagerST {
    // Singleton
    private static @Nullable AuthTokenManagerST _instance;

    // Preferences
    private static final String _PREFERENCES_FILENAME = "authData";
    private final SharedPreferences _preferences;

    // Keys
    private static final String _KEY_ACCESS_TOKEN = "accessToken";

    // Cache
    private String _token;

    public static synchronized void createInstance(Context context) {
        if (_instance != null) return;
        _instance = new AuthTokenManagerST(context);
    }

    public static synchronized AuthTokenManagerST getInstance() {
        if (_instance == null)
            throw new RuntimeException("AuthTokenManagerST has not been initialized");
        return _instance;
    }

    public AuthTokenManagerST(Context context) {
        this._preferences = this._createEncryptedPreferences(context);
    }

    private SharedPreferences _createEncryptedPreferences(Context ctx) {
        try {
            return EncryptedSharedPreferences.create(
                    _PREFERENCES_FILENAME,
                    MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC),
                    ctx,
                    EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                    EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            );
        } catch (GeneralSecurityException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void clear() {
        _preferences.edit().clear().apply();
    }

    public boolean hasToken() {
        return this.getToken() != null;
    }

    public void saveToken(String token) {
        this._token = token;
        _preferences.edit().putString(_KEY_ACCESS_TOKEN, token).apply();
    }

    public @Nullable String getToken() {
        if (this._token != null) return this._token;
        this._token = _preferences.getString(_KEY_ACCESS_TOKEN, null);
        return this._token;
    }
}
