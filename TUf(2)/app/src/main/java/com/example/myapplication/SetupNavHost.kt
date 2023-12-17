package com.example.myapplication
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.mad.notesibk.login.LoginViewModel
import kotlinx.coroutines.flow.MutableStateFlow

@Composable
fun SetupNavHost(navController: NavHostController) {
    val events = remember { mutableStateListOf<Event>() }
    val eventsFlow = remember { MutableStateFlow<List<Event>>(events) }
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
        composable("Meals/{experience}") {backStackEntry ->
            val experience = backStackEntry.arguments?.getString("experience") ?: "Beginner"
            Meals(navController = navController, selectedExperience = experience)
        }
        composable(
            "Booking/{eventId}",
            arguments = listOf(navArgument("eventId") { type = NavType.IntType })
        ) { backStackEntry ->
            val experience = backStackEntry.arguments?.getString("experience") ?: "Beginner"
            val eventId = backStackEntry.arguments?.getInt("eventId")
            Booking(navController, selectedExperience = experience, eventId, eventsFlow)
        }

        composable("Booking") {backStackEntry ->
            val experience = backStackEntry.arguments?.getString("experience") ?: "Beginner"
            Booking(navController, selectedExperience = experience, null, eventsFlow)
        }
        composable("Saved") {backStackEntry ->
            val experience = backStackEntry.arguments?.getString("experience") ?: "Beginner"
            Saved(navController = navController, selectedExperience = experience)
        }
        composable("Account") { backStackEntry ->
            val experience = backStackEntry.arguments?.getString("experience") ?: "Beginner"
            Account(navController = navController, selectedExperience = experience)
        }
        composable("contact") { backStackEntry ->
            val experience = backStackEntry.arguments?.getString("experience") ?: "Beginner"
            Contact(navController = navController, selectedExperience = experience)
        }
        composable("Eplans/{experience}") { backStackEntry ->
            val experience = backStackEntry.arguments?.getString("experience") ?: "Beginner"
            Eplans(navController = navController, selectedExperience = experience)
        }
        composable("Mplans/{experience}") {backStackEntry ->
            val experience = backStackEntry.arguments?.getString("experience") ?: "Beginner"
            Mplans(navController = navController, selectedExperience = experience)
        }
        composable("Sched") {backStackEntry ->
            val experience = backStackEntry.arguments?.getString("experience") ?: "Beginner"
            Sched2(navController = navController, selectedExperience = experience)
        }
    }
}

