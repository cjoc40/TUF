package com.example.myapplication
// SetupNavHost.kt
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun SetupNavHost() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "login"
    ) {
        composable("login") {
            // Pass the NavController to the Login composable
            Login(navController = navController)
        }

        composable("otherPage") {
            // Your other page composable
            Exercise()
        }
    }
}

