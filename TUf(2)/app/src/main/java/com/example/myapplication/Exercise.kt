package com.example.myapplication

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.messaging.FirebaseMessaging

val database = FirebaseDatabase.getInstance()
val exercisesRef = database.getReference("exercises")

fun saveExercises(exercises: List<Exercise>) {
    exercises.forEachIndexed { index, exercise ->
        exercisesRef.child(index.toString()).setValue(exercise)
    }
}

data class Exercise(val name: String, val details: String)
@Composable
fun Exercise(navController: NavHostController) {
    var expanded by remember { mutableStateOf(false) }
    var selectedExperience by remember { mutableStateOf("Select an Experience Level") }
    val experienceLevels = listOf("Beginner", "Intermediate")
    var dropdownPosition by remember { mutableStateOf(Offset.Zero) }

    FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
        if (!task.isSuccessful) {
            Log.w("FCM", "Fetching FCM registration token failed", task.exception)
            return@OnCompleteListener
        }
        val token = task.result
        Log.d("FCM", "FCM Token: $token")
    })

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
                        .clickable {
                            navController.navigate("Account")
                        }
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 200.dp, start = 16.dp, end = 16.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                ) {
                    OutlinedTextField(
                        value = selectedExperience,
                        onValueChange = { },
                        label = { Text("Experience Level") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .onGloballyPositioned { layoutCoordinates ->
                                dropdownPosition = layoutCoordinates.positionInRoot()
                            },
                        readOnly = true
                    )
                    Box(
                        modifier = Modifier
                            .matchParentSize()
                            .clickable { expanded = true }
                    )
                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false },
                        offset = DpOffset(dropdownPosition.x.dp, dropdownPosition.y.dp + 56.dp)
                    ) {
                        experienceLevels.forEach { level ->
                            DropdownMenuItem(onClick = {
                                selectedExperience = level
                                expanded = false
                            }) {
                                Text(level)
                            }
                        }
                    }
                }
                androidx.compose.material.Button(
                    onClick = {
                        navController.navigate("Saved")
                    },
                    modifier = Modifier
                        .wrapContentWidth()
                        .padding(top = 16.dp)
                        .align(Alignment.CenterHorizontally)
                        .background(Color.Gray),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Gray),
                ) {
                    androidx.compose.material.Text("Save", color = Color.White)
                }

                Spacer(modifier = Modifier.height(8.dp))

                androidx.compose.material.Button(
                    onClick = {
                        navController.navigate("Eplans/$selectedExperience")
                    },
                    modifier = Modifier
                        .wrapContentWidth()
                        .align(Alignment.CenterHorizontally)
                        .background(Color.Gray),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Gray),
                ) {
                    androidx.compose.material.Text("See Exercises", color = Color.White)
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
@Composable
fun ClickableImage(resourceId: Int, onClick: () -> Unit, modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(resourceId),
        contentDescription = null,
        modifier = modifier.clickable { onClick() }
    )
}


