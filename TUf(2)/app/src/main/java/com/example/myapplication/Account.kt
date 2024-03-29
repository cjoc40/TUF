package com.example.myapplication

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.firebase.auth.FirebaseAuth

@Composable
fun Account(navController: NavHostController, selectedExperience: String) {
    val auth = FirebaseAuth.getInstance()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Color(
                    red = 0.6392157f,
                    green = 0.5803922f,
                    blue = 0.38039216f,
                    alpha = 1f
                )
            )
            .padding(0.dp)
            .alpha(1f)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Color(
                        red = 0.6392157f,
                        green = 0.5803922f,
                        blue = 0.38039216f,
                        alpha = 1f
                    )
                )
                .padding(0.dp)
                .alpha(1f)
                .clip(RoundedCornerShape(0.dp))
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(130.dp)
                    .background(Color(red = 0f, green = 0f, blue = 0f, alpha = 1f))
                    .padding(horizontal = 16.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(R.drawable.tus),
                    contentDescription = null,
                    modifier = Modifier
                        .size(210.dp)
                        .align(Alignment.CenterStart)
                )
                Spacer(modifier = Modifier.width(16.dp))
                Image(
                    painter = painterResource(R.drawable.profile),
                    contentDescription = null,
                    modifier = Modifier
                        .size(85.dp)
                        .align(Alignment.CenterEnd)
                )
            }
            Column(
                modifier = Modifier
                    .padding(top = 200.dp)
            ) {
                androidx.compose.material3.Text(
                    text = "Account settings",
                    fontSize = 24.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    textAlign = TextAlign.Center
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    onClick = {},
                    modifier = Modifier
                        .wrapContentWidth()
                        .padding(top = 16.dp)
                        .background(Color.Gray),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Gray),
                ) {
                    Text("Privacy", color = Color.White)
                }
                Button(
                    onClick = {
                        navController.navigate("contact")
                    },
                    modifier = Modifier
                        .wrapContentWidth()
                        .padding(top = 16.dp)
                        .background(Color.Gray),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Gray),
                ) {
                    Text("Contact us", color = Color.White)
                }
                Button(
                    onClick = {},
                    modifier = Modifier
                        .wrapContentWidth()
                        .padding(top = 16.dp)
                        .background(Color.Gray),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Gray),
                ) {
                    Text("Personal info", color = Color.White)
                }
                Button(
                    onClick = {
                        navController.navigate("login2")
                        auth.signOut()
                    },
                    modifier = Modifier
                        .wrapContentWidth()
                        .padding(top = 16.dp)
                        .background(Color.Gray),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Gray),
                ) {
                    Text("Sign out", color = Color.White)
                }
            }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp)
                        .background(Color.Black)
                        .align(Alignment.BottomCenter)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        ClickableImage(
                            resourceId = R.drawable.meals,
                            onClick = {
                                navController.navigate("Meals/$selectedExperience")
                            },
                            modifier = Modifier.size(80.dp)
                        )
                        ClickableImage(
                            resourceId = R.drawable.exercises,
                            onClick = {
                                navController.navigate("Exercise")
                            },
                            modifier = Modifier.size(80.dp)
                        )
                        ClickableImage(
                            resourceId = R.drawable.booking,
                            onClick = {
                                navController.navigate("Booking")
                            },
                            modifier = Modifier.size(80.dp)
                        )
                    }
                }
            }
        }
    }

