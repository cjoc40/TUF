package hoods.com.notes

import androidx.compose.runtime.Composable
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import hoods.com.notes.home.Exercise
import hoods.com.notes.login.LoginScreen
import hoods.com.notes.login.LoginViewModel
import hoods.com.notes.login.SignUpScreen

enum class LoginRoutes {
    Signup,
    SignIn
}

enum class HomeRoutes {
    Home,
    Exercise
}

enum class NestedRoutes {
    Main,
    Login
}

@Composable
fun Navigation(navController: NavHostController = rememberNavController(), loginViewModel: LoginViewModel) {
    NavHost(
        navController = navController,
        startDestination = NestedRoutes.Login.name
    ) {
        authGraph(navController, loginViewModel)
        homeGraph(navController)
    }

}
fun NavGraphBuilder.authGraph(navController: NavHostController, loginViewModel: LoginViewModel) {
    navigation(
        startDestination = LoginRoutes.SignIn.name,
        route = NestedRoutes.Login.name
    ) {
        composable(route = LoginRoutes.SignIn.name) {
            LoginScreen(
                navController = navController,
                loginViewModel = loginViewModel,
                onNavToSignUpPage = {
                    navController.navigate(LoginRoutes.Signup.name) {
                        launchSingleTop = true
                        popUpTo(route = NestedRoutes.Login.name) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        composable(route = LoginRoutes.Signup.name) {
            SignUpScreen(
                navController = navController,
                onNavToSignInPage = {
                    navController.navigate(LoginRoutes.SignIn.name) // Corrected destination for sign-in
                }
            )
        }
    }
}


fun NavGraphBuilder.homeGraph(navController: NavHostController) {
    navigation(
        startDestination = HomeRoutes.Home.name,
        route = NestedRoutes.Main.name,
    ) {
        composable(HomeRoutes.Home.name) {
            Exercise(navController = navController) {
                navController.navigate(NestedRoutes.Login.name) {
                    launchSingleTop = true
                    popUpTo(NestedRoutes.Login.name) { // Change this to pop up to the login route
                        inclusive = true
                    }
                }
            }
        }

        composable(
            route = HomeRoutes.Exercise.name + "?id={id}",
            arguments = listOf(navArgument("id") {
                type = NavType.StringType
                defaultValue = ""
            })
        ) { entry ->
            // Handle detail screen if needed
        }
    }
}


