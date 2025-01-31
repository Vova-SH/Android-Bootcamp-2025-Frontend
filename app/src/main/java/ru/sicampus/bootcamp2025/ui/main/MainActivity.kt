package com.example.myapplication.ui.main

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication.R
import com.example.myapplication.core.SettingConstants
import com.example.myapplication.ui.login.AuthActivity

class MainActivity : AppCompatActivity() {
    private lateinit var settings: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.activity_main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        settings = getSharedPreferences(SettingConstants.PREFS_FILE, Context.MODE_PRIVATE)

        if (settings.getString(SettingConstants.PREF_TOKEN, SettingConstants.DEF_VALUE) == SettingConstants.DEF_VALUE) {
            Log.d("Test", "(MainActivity) Пользователь не зарегистрирован, запускаем регистрацию.")
            startActivity(Intent(this, AuthActivity::class.java))
            finish()
            return
        }
    }
}