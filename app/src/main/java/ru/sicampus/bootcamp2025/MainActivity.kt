package ru.sicampus.bootcamp2025

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ru.sicampus.bootcamp2025.ui.navigation.screens.EditProfileScreen
import ru.sicampus.bootcamp2025.ui.navigation.screens.ProfileScreen
import ru.sicampus.bootcamp2025.ui.navigation.screens.Screen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(
                navController = navController,
                startDestination = Screen.Profile.route
            ) {
                composable(Screen.Profile.route) {
                    ProfileScreen(
                        onEditClick = { navController.navigate(Screen.EditProfile.route) } ,
                        onCancel = { navController.navigate(Screen.EditProfile.route) }
                    )
                }
                composable(Screen.EditProfile.route) {
                    EditProfileScreen(
                        onCancel = { navController.navigate(Screen.Profile.route) },
                        onSave = { navController.popBackStack() },
                    )
                }
            }
        }
    }
}