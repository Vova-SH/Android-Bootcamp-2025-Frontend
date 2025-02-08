package ru.sicampus.bootcamp2025

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import ru.sicampus.bootcamp2025.data.auth.storage.AuthTokenManagerST
import ru.sicampus.bootcamp2025.ui.auth.AuthFragment
import ru.sicampus.bootcamp2025.ui.list.ListFragment
import ru.sicampus.bootcamp2025.ui.profile.ProfileFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AuthTokenManagerST.createInstance(this)

        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        if (isUserAuthenticated()) loadFragment(ProfileFragment()) else loadFragment(AuthFragment())
    }

    private fun isUserAuthenticated(): Boolean {
        return AuthTokenManagerST.getInstance().hasToken()  // FIXME
    }

    private fun loadFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.main, fragment)
        transaction.commit()
    }
}