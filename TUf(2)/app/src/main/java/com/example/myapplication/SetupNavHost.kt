package com.example.myapplication
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mad.notesibk.login.LoginViewModel

@Composable
fun SetupNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "login"
    ) {
        composable("login") {
            Login(navController = navController)
        }
        composable("login2") {
            val viewModel = LoginViewModel()
            Login2(navController, viewModel, LocalContext.current)
        }
        composable("Exercise") {
            Exercise(navController = navController)
        }
        composable("Meals") {
            Meals(navController = navController)
        }
        composable("Booking") {
            Booking(navController = navController)
        }
        composable("Saved") {
            Saved(navController = navController)
        }
        composable("Account") {
            Account(navController = navController)
        }
        composable("contact") {
            Contact(navController = navController)
        }
        composable("Eplans") {
            Eplans(navController = navController)
        }
        composable("Mplans") {
            Mplans(navController = navController)
        }
        composable("Sched") {
            Sched2(navController = navController)
        }
    }
}

