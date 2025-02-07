package ru.sicampus.bootcamp2025

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ru.sicampus.bootcamp2025.ui.navigation.Screen
import ru.sicampus.bootcamp2025.ui.screens.AuthorizationScreen
import ru.sicampus.bootcamp2025.ui.screens.EditProfileScreen
import ru.sicampus.bootcamp2025.ui.screens.MainScreen
import ru.sicampus.bootcamp2025.ui.screens.ProfileScreen
import ru.sicampus.bootcamp2025.ui.screens.RegistrationScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        setContent {
            val navController = rememberNavController()
            NavHost(
                navController = navController,
                startDestination = Screen.AuthorizationScreen.route
            ) {
                composable(Screen.RegistrationScreen.route) {
                    RegistrationScreen(
                        onRegistre = { navController.navigate(Screen.MainScreen.route)},
                        onBack = { navController.popBackStack() },
                    )
                }
                composable(Screen.AuthorizationScreen.route) {
                    AuthorizationScreen(
                        toRegistreScreen = {navController.navigate(Screen.RegistrationScreen.route)},
                        onSignIn = { navController.navigate(Screen.MainScreen.route) }
                    )
                }

                composable(Screen.MainScreen.route) {
                    MainScreen(
                        //onCancel = { navController.navigate(Screen.Profile.route) },
                        toAuthorizationScreen = { navController.navigate(Screen.AuthorizationScreen.route) },
                        toProfileScreen = { navController.navigate(Screen.Profile.route)}
                    )
                }

                composable(Screen.Profile.route) {
                    ProfileScreen(
                        onEditClick = { navController.navigate(Screen.EditProfile.route) } ,
                        toMainScreen = { navController.navigate(Screen.MainScreen.route) }
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