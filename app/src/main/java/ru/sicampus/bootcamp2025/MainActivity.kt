package ru.sicampus.bootcamp2025

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val userRole = intent.getStringExtra("USER_ROLE")

        setContentView(R.layout.activity_main)


        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragment_container) as NavHostFragment
        val navController = navHostFragment.navController
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        // Изменяем меню в зависимости от роли
        if (userRole == "ROLE_USER") {
            bottomNavigationView.menu.clear()  // Очищаем меню
            bottomNavigationView.inflateMenu(R.menu.bottom_menu)  // Загружаем меню для пользователя

            // Устанавливаем граф для пользователя
            navController.setGraph(R.navigation.main_nav_graph)

        } else {
            bottomNavigationView.menu.clear()  // Очищаем меню
            bottomNavigationView.inflateMenu(R.menu.bottom_menu_admin)  // Загружаем меню для администратора

            // Устанавливаем граф для администратора
            navController.setGraph(R.navigation.main_admin_nav_graph)
        }


        bottomNavigationView.setupWithNavController(navController)

    }
}