package com.example.myapplication


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
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
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                val navController = rememberNavController()

                // Set up navigation host
                NavHost(navController = navController, startDestination = "login") {
                    // Login page
                    composable("login") {
                        Login(navController = navController)
                    }
                    composable("login2") {
                        Login2()
                    }
                    // Second page
                    composable("secondPage") {
                        Exercise()
                    }
                }
            }
        }
    }
}

@Composable
fun Login(navController: NavHostController) {
    val emailState = remember { mutableStateOf("") }
    val passwordState = remember { mutableStateOf("") }
    val experienceState = remember { mutableStateOf("") }
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

                // Experience Label and Field
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Experience:",
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

                    // Experience Text Field
                    BasicTextField(
                        value = experienceState.value,
                        onValueChange = { experienceState.value = it },
                        modifier = Modifier
                            .padding(start = 8.dp)
                            .width(200.dp)
                            .height(28.dp)
                            .background(Color.White, shape = RoundedCornerShape(8.dp)),
                    )
                }
                Button(
                    onClick = {
                        // Navigate to the second page when the button is clicked
                        navController.navigate("secondPage")
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                ) {
                    Text("Register")
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
                ClickableText(
                    text = AnnotatedString("Have an account?"),
                    onClick = {
                        // Handle the click action here (e.g., navigate to a password recovery screen)
                        // For now, let's navigate to the second page for demonstration purposes
                        navController.navigate("login2")
                    },
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .alpha(1f)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginPreview() {
    MyApplicationTheme {
        val navController = rememberNavController()
        Login(navController = navController)
    }
}

