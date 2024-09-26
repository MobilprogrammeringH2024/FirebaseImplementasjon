package com.example.firebaseimplementation.ui.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.firebaseimplementation.service.impl.AccountServiceImpl
import com.example.firebaseimplementation.service.module.FirebaseModule
import com.example.firebaseimplementation.ui.screens.LoginSelection.LoginSelectionScreen
import com.example.firebaseimplementation.ui.screens.login.LoginScreen
import com.example.firebaseimplementation.ui.screens.registration.RegistrationScreen
import com.example.firebaseimplementation.ui.screens.text.TextScreen


@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val accountService = remember { AccountServiceImpl(FirebaseModule.auth()) }

    Scaffold(
        modifier = Modifier.fillMaxSize()
   ) { innerPadding ->

        NavHost(
            navController = navController,
            startDestination =
            if (accountService.hasUser) {
                AppScreens.TEXT.name
            } else {
                AppScreens.LOGIN_SELECTION.name
            },
            modifier = Modifier.padding(innerPadding)
        ) {

            composable(AppScreens.LOGIN.name) {
                LoginScreen(
                    navController = navController,
                )
            }

            composable(AppScreens.LOGIN_SELECTION.name) {
                LoginSelectionScreen(
                    navController = navController,
                )
            }

            composable(AppScreens.REGISTRATION.name) {
                RegistrationScreen(
                    navController = navController
                )
            }

            composable(AppScreens.TEXT.name) {
                TextScreen(
                    navController = navController
                )
            }

        }
    }
}