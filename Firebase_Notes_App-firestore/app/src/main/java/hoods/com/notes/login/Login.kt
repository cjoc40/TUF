package hoods.com.notes.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import hoods.com.notes.HomeRoutes
import hoods.com.notes.ui.theme.NotesTheme

@Composable
fun LoginScreen(
    navController: NavHostController,  // Add NavHostController parameter
    loginViewModel: LoginViewModel? = null,
    onNavToSignUpPage: () -> Unit,

) {
    val emailState = remember { mutableStateOf("") }
    val passwordState = remember { mutableStateOf("") }
    val context = LocalContext.current
    val errorMessageState = remember { mutableStateOf("") }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Color(
                    red = 0.6392157077789307f,
                    green = 0.5803921818733215f,
                    blue = 0.3803921639919281f,
                    alpha = 0f
                )
            )
            .padding(0.dp)
            .alpha(1f)
    ) {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(red = 0.6392157077789307f, green = 0.5803921818733215f, blue = 0.3803921639919281f, alpha = 1f))
                .padding(0.dp)
                .alpha(1f)
                .clip(RectangleShape)
        ) {

            // Background Boxes
            Box(
                modifier = Modifier
                    .width(720.dp)
                    .height(152.dp)
                    .background(Color(red = 0f, green = 0f, blue = 0f, alpha = 1f))
            )
            Box(
                modifier = Modifier
                    .width(475.dp)
                    .height(152.dp)
                    .background(Color.Transparent)
            )
            Box(
                modifier = Modifier
                    .width(145.dp)
                    .height(133.dp)
                    .background(Color.Transparent)
            )
            Box(
                modifier = Modifier
                    .width(224.dp)
                    .height(71.dp)
                    .background(Color(red = 0.8509804010391235f, green = 0.8509804010391235f, blue = 0.8509804010391235f, alpha = 1f))
            )
            Box(
                modifier = Modifier
                    .width(224.dp)
                    .height(71.dp)
                    .background(Color(red = 0.8509804010391235f, green = 0.8509804010391235f, blue = 0.8509804010391235f, alpha = 1f))
            )

            // Text Elements and Input Fields
            Column(
                modifier = Modifier
                    .padding(top = 200.dp)
            ) {
                Text(
                    text = "Log in",
                    style = MaterialTheme.typography.h3,
                    fontWeight = FontWeight.Black,
                    color = Color.Black, // Set the text color to black
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                // Top Banner
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Email:",
                        textAlign = TextAlign.Start,
                        fontSize = 28.sp,
                        textDecoration = TextDecoration.None,
                        letterSpacing = 0.sp,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier
                            .padding(bottom = 8.dp)
                            .alpha(1f),
                        color = Color(red = 0f, green = 0f, blue = 0f, alpha = 1f),
                        fontWeight = FontWeight.Bold,
                        fontStyle = FontStyle.Normal,
                    )

                    // Email Text Field
                    BasicTextField(
                        value = emailState.value,
                        onValueChange = { emailState.value = it },
                        modifier = Modifier
                            .padding(start = 8.dp)
                            .width(200.dp)
                            .height(28.dp)
                            .background(Color.White, shape = RoundedCornerShape(8.dp)),
                    )
                }

                // Password Label and Field
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Password:",
                        textAlign = TextAlign.Start,
                        fontSize = 28.sp,
                        textDecoration = TextDecoration.None,
                        letterSpacing = 0.sp,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier
                            .padding(bottom = 8.dp)
                            .alpha(1f),
                        color = Color(red = 0f, green = 0f, blue = 0f, alpha = 1f),
                        fontWeight = FontWeight.Bold,
                        fontStyle = FontStyle.Normal,
                    )

                    // Password Text Field
                    BasicTextField(
                        value = passwordState.value,
                        onValueChange = { passwordState.value = it },
                        modifier = Modifier
                            .padding(start = 8.dp)
                            .width(200.dp)
                            .height(28.dp)
                            .background(Color.White, shape = RoundedCornerShape(8.dp)),
                    )
                }
                Button(
                    onClick = {
                        // Validate email and password here if needed
                        val email = emailState.value
                        val password = passwordState.value

                        if (email.isNotBlank() && password.isNotBlank()) {
                            // Handle login logic here using loginViewModel
                            val loginSuccessful = loginViewModel?.loginUser()

                            if (loginSuccessful == true) {
                                // Navigate to the Exercise page
                                navController.navigate(HomeRoutes.Exercise.name) // Change this to the correct destination
                            } else {
                                errorMessageState.value = "Login failed. Please check your credentials."
                            }
                        } else {
                            errorMessageState.value = "Email and password cannot be empty."
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.White)
                ) {
                    Text("Login")
                }


                // Forgot Password Text
                Text(
                    text = "Forgot your password?",
                    textAlign = TextAlign.Start,
                    fontSize = 22.sp,
                    textDecoration = TextDecoration.None,
                    letterSpacing = 0.sp,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .alpha(1f),
                    color = Color(red = 0f, green = 0f, blue = 0f, alpha = 1f),
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Normal,
                )
                Button(
                    onClick = {
                        onNavToSignUpPage.invoke()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.White)
                ) {
                    Text("Sign Up")
                }
            }
        }
    }
}

@Composable
fun SignUpScreen(navController: NavHostController, onNavToSignInPage: () -> Unit,)
{
    val userNameState = remember { mutableStateOf("") }
    val passwordState = remember { mutableStateOf("") }
    val confirmPasswordState = remember { mutableStateOf("") }
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Color(
                    red = 0.6392157077789307f,
                    green = 0.5803921818733215f,
                    blue = 0.3803921639919281f,
                    alpha = 0f
                )
            )
            .padding(0.dp)
            .alpha(1f)
    ) {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(red = 0.6392157077789307f, green = 0.5803921818733215f, blue = 0.3803921639919281f, alpha = 1f))
                .padding(0.dp)
                .alpha(1f)
                .clip(RectangleShape)
        ) {

            // Background Boxes
            Box(
                modifier = Modifier
                    .width(720.dp)
                    .height(152.dp)
                    .background(Color(red = 0f, green = 0f, blue = 0f, alpha = 1f))
            )
            Box(
                modifier = Modifier
                    .width(475.dp)
                    .height(152.dp)
                    .background(Color.Transparent)
            )
            Box(
                modifier = Modifier
                    .width(145.dp)
                    .height(133.dp)
                    .background(Color.Transparent)
            )
            Box(
                modifier = Modifier
                    .width(224.dp)
                    .height(71.dp)
                    .background(Color(red = 0.8509804010391235f, green = 0.8509804010391235f, blue = 0.8509804010391235f, alpha = 1f))
            )
            Box(
                modifier = Modifier
                    .width(224.dp)
                    .height(71.dp)
                    .background(Color(red = 0.8509804010391235f, green = 0.8509804010391235f, blue = 0.8509804010391235f, alpha = 1f))
            )

            // Text Elements and Input Fields
            Column(
                modifier = Modifier
                    .padding(top = 200.dp)
            ) {
                // Top Banner
                Text(
                    text = "Sign Up",
                    style = MaterialTheme.typography.h3,
                    fontWeight = FontWeight.Black,
                    color = Color.Black, // Set the text color to black
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                // Email Label and Field
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Email:",
                        textAlign = TextAlign.Start,
                        fontSize = 28.sp,
                        textDecoration = TextDecoration.None,
                        letterSpacing = 0.sp,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier
                            .padding(bottom = 8.dp)
                            .alpha(1f),
                        color = Color(red = 0f, green = 0f, blue = 0f, alpha = 1f),
                        fontWeight = FontWeight.Bold,
                        fontStyle = FontStyle.Normal,
                    )

                    // Email Text Field
                    BasicTextField(
                        value = userNameState.value,
                        onValueChange = { userNameState.value = it },
                        modifier = Modifier
                            .padding(start = 8.dp)
                            .width(200.dp)
                            .height(28.dp)
                            .background(Color.White, shape = RoundedCornerShape(8.dp)),
                    )
                }

                // Password Label and Field
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Password:",
                        textAlign = TextAlign.Start,
                        fontSize = 28.sp,
                        textDecoration = TextDecoration.None,
                        letterSpacing = 0.sp,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier
                            .padding(bottom = 8.dp)
                            .alpha(1f),
                        color = Color(red = 0f, green = 0f, blue = 0f, alpha = 1f),
                        fontWeight = FontWeight.Bold,
                        fontStyle = FontStyle.Normal,
                    )

                    // Password Text Field
                    BasicTextField(
                        value = passwordState.value,
                        onValueChange = { passwordState.value = it },
                        modifier = Modifier
                            .padding(start = 8.dp)
                            .width(200.dp)
                            .height(28.dp)
                            .background(Color.White, shape = RoundedCornerShape(8.dp)),
                    )
                }

                // Confirm Password Label and Field
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Confirm Password:",
                        textAlign = TextAlign.Start,
                        fontSize = 28.sp,
                        textDecoration = TextDecoration.None,
                        letterSpacing = 0.sp,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier
                            .padding(bottom = 8.dp)
                            .alpha(1f),
                        color = Color(red = 0f, green = 0f, blue = 0f, alpha = 1f),
                        fontWeight = FontWeight.Bold,
                        fontStyle = FontStyle.Normal,
                    )

                    // Confirm Password Text Field
                    BasicTextField(
                        value = confirmPasswordState.value,
                        onValueChange = { confirmPasswordState.value = it },
                        modifier = Modifier
                            .padding(start = 8.dp)
                            .width(200.dp)
                            .height(28.dp)
                            .background(Color.White, shape = RoundedCornerShape(8.dp)),
                    )
                }
                Button(
                    onClick = {
                        onNavToSignInPage.invoke()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.White)
                ) {
                    Text("Sign Up")
                }

// Already have an account Text
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                ) {
                    Text(text = "Already have an account?")
                    Spacer(modifier = Modifier.size(8.dp))
                    TextButton(onClick = onNavToSignInPage) {
                        Text(text = "Sign In")
                    }
                }


            }
        }
    }
}
@Preview(showSystemUi = true)
@Composable
fun PrevLoginScreen() {
    NotesTheme {
        val navController = rememberNavController()

        val loginViewModel = LoginViewModel()

        NavHost(
            navController = navController,
            startDestination = "loginPage"
        ) {
            composable("loginPage") {
                LoginScreen(
                    navController = navController,
                    loginViewModel = loginViewModel,
                    onNavToSignUpPage = {
                        navController.navigate("signUpPage")
                    }
                )
            }

            composable("signUpPage") {
                SignUpScreen(
                    navController = navController,
                    onNavToSignInPage = {
                        // Provide the navigation logic for signing in
                        navController.navigate("signInPage")
                    }
                )
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun PrevSignUpScreen() {
    NotesTheme {
        val navController = rememberNavController()
        SignUpScreen(navController = navController, onNavToSignInPage = {})
    }
}
