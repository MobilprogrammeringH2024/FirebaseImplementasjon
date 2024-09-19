package com.example.firebaseimplementation.ui.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.firebaseimplementation.ui.navigation.navBars.BottomNavBar
import com.example.firebaseimplementation.ui.navigation.navBars.TopBar
import com.example.firebaseimplementation.ui.navigation.navBars.getCurrentScreen
import com.example.firebaseimplementation.ui.screens.login.LoginScreen
import com.example.firebaseimplementation.ui.screens.registration.RegistrationScreen


@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val currentScreen = getCurrentScreen(navController)

    Scaffold(
        topBar = {
            when(currentScreen) {
                else -> TopBar(navController)
            }
        },
        bottomBar = {
            when(currentScreen) {
                AppScreens.LOGIN.name -> {  }
                AppScreens.REGISTRATION.name -> {  }
                else -> BottomNavBar(navController)
            }
        },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->

        NavHost(
            navController = navController,
            startDestination = AppScreens.LOGIN.name,
            modifier = Modifier.padding(innerPadding)
        ) {

            composable(AppScreens.LOGIN.name) {
                LoginScreen(
                    navController= navController,
                )
            }

            composable(AppScreens.REGISTRATION.name) {
                RegistrationScreen(
                    navController = navController
                )
            }

            composable(AppScreens.TODO.name) {

            }

        }

    }
}