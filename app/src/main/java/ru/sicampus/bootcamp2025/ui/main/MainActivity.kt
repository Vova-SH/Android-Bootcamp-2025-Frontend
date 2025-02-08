package ru.sicampus.bootcamp2025.ui.main

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.view.View // Добавляем импорт
import ru.sicampus.bootcamp2025.R
import ru.sicampus.bootcamp2025.data.save.PrefsManager
import ru.sicampus.bootcamp2025.databinding.ActivityMainBinding
import ru.sicampus.bootcamp2025.ui.list.ListFragment
import ru.sicampus.bootcamp2025.ui.mainscreen.MainScreenFragment
import ru.sicampus.bootcamp2025.ui.profile.ProfileFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    fun showBottomNavigation() {
        binding.bottomNav.visibility = View.VISIBLE
    }

    fun hideBottomNavigation() {
        binding.bottomNav.visibility = View.GONE
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        hideBottomNavigation()

        PrefsManager.init(this)

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { view, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.setPadding(
                systemBars.left,
                systemBars.top,
                systemBars.right,
                systemBars.bottom
            )
            insets
        }

        binding.bottomNav.setup(
            items = listOf(
                BottomNavigationBar.NavItem(
                    title = getString(R.string.menu_list),
                    iconResId = R.drawable.ic_local_foreground
                ),
                BottomNavigationBar.NavItem(
                    title = getString(R.string.menu_main),
                    iconResId = R.drawable.ic_home_foreground
                ),
                BottomNavigationBar.NavItem(
                    title = getString(R.string.menu_profile),
                    iconResId = R.drawable.ic_user_foreground
                )
            ),
        ) { index ->
            when (index) {
                0 -> supportFragmentManager.beginTransaction()
                    .replace(R.id.main, ListFragment())
                    .commit()
                1 -> supportFragmentManager.beginTransaction()
                    .replace(R.id.main, MainScreenFragment())
                    .commit()
                2 -> supportFragmentManager.beginTransaction()
                    .replace(R.id.main, ProfileFragment())
                    .commit()
            }
        }

        binding.bottomNav.updateSelectedIndex(1)
    }
}