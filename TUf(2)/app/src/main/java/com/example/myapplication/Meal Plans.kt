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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
data class MealPlan(val id: Int, val name: String, val description: String)

@Composable
fun Mplans(navController: NavHostController, selectedExperience: String) {
        val mealPlansLiveData = remember { mutableStateOf<List<MealPlan>>(emptyList()) }

        LaunchedEffect(Unit) {
            val database = FirebaseDatabase.getInstance("https://ctuf-6a9e3-default-rtdb.europe-west1.firebasedatabase.app/")
            val referencePath = if (selectedExperience == "Intermediate") {
                "mealsa"
            } else {
                "mealsb"
            }
            val myRef = database.getReference(referencePath)

            myRef.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    val newMealPlans = mutableListOf<MealPlan>()

                    for (mealSnapshot in dataSnapshot.children) {
                        val id = mealSnapshot.child("id").getValue(Long::class.java)
                        val name = mealSnapshot.child("name").getValue(String::class.java)
                        val description = mealSnapshot.child("description").getValue(String::class.java)

                        if (id != null && name != null && description != null) {
                            val mealPlan = MealPlan(id.toInt(), name, description)
                            newMealPlans.add(mealPlan)
                        }
                    }

                    mealPlansLiveData.value = newMealPlans
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.e("Firebase", "Error fetching data: ${error.message}")
                }
            })
        }
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
                .fillMaxWidth()
                .height(130.dp)
                .background(Color(red = 0f, green = 0f, blue = 0f, alpha = 1f))
                .padding(start = 16.dp, top = 8.dp, end = 16.dp),
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
                .padding(start = 16.dp, top = 130.dp, end = 16.dp, bottom = 16.dp)
        ) {
            Text(
                text = "Meal plans",
                textAlign = TextAlign.Start,
                fontSize = 48.sp,
                textDecoration = TextDecoration.None,
                letterSpacing = 0.sp,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .fillMaxWidth()
                    .alpha(1f),
                color = Color(red = 0f, green = 0f, blue = 0f, alpha = 1f),
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Normal,
            )

            Spacer(modifier = Modifier.height(16.dp))

            mealPlansLiveData.value.forEach { mealPlan ->
                Mplan(mealPlan.name, mealPlan.description)
                Spacer(modifier = Modifier.height(8.dp))
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
@Composable
fun Mplan(planTitle: String, planDetails: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(130.dp)
            .background(Color.Gray)
    ) {
        Text(
            text = planTitle,
            textAlign = TextAlign.Start,
            fontSize = 25.sp,
            textDecoration = TextDecoration.None,
            letterSpacing = 0.sp,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .align(Alignment.TopStart)
                .width(126.dp)
                .alpha(1f),
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Normal,
        )

        Text(
            text = planDetails,
            textAlign = TextAlign.Start,
            fontSize = 16.sp,
            textDecoration = TextDecoration.None,
            letterSpacing = 0.sp,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(16.dp)
                .width(300.dp)
                .alpha(1f),
            color = Color.White,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal,
        )
    }
}
