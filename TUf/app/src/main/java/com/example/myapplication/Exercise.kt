package com.example.myapplication

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun Exercise() {
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
                .background(Color(red = 0.6392157f, green = 0.5803922f, blue = 0.38039216f, alpha = 1f))
                .padding(0.dp)
                .alpha(1f)
                .clip(RoundedCornerShape(0.dp))
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
                    .width(320.dp)
                    .height(71.dp)
                    .background(Color(red = 0.8509804f, green = 0.8509804f, blue = 0.8509804f, alpha = 1f))
            )
            Box(
                modifier = Modifier
                    .width(320.dp)
                    .height(71.dp)
                    .background(Color(red = 0.8509804f, green = 0.8509804f, blue = 0.8509804f, alpha = 1f))
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
                        text = "Exercises:",
                        textAlign = TextAlign.Start,
                        fontSize = 20.sp,
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

                    // Exercise Text Field
                    BasicTextField(
                        value = TextFieldValue(),
                        onValueChange = {},
                        modifier = Modifier
                            .padding(start = 8.dp)
                            .width(200.dp)
                            .height(28.dp)
                            .background(Color.White, shape = RoundedCornerShape(8.dp)),
                    )
                }

                // Calorie Total Label and Field
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Calorie total:",
                        textAlign = TextAlign.Start,
                        fontSize = 20.sp,
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

                    // Calorie Total Text Field
                    BasicTextField(
                        value = TextFieldValue(),
                        onValueChange = {},
                        modifier = Modifier
                            .padding(start = 8.dp)
                            .width(200.dp)
                            .height(28.dp)
                            .background(Color.White, shape = RoundedCornerShape(8.dp)),
                    )
                }

                // Date Label and Field
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Date:",
                        textAlign = TextAlign.Start,
                        fontSize = 20.sp,
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

                    // Date Text Field
                    BasicTextField(
                        value = TextFieldValue(),
                        onValueChange = {},
                        modifier = Modifier
                            .padding(start = 8.dp)
                            .width(200.dp)
                            .height(28.dp)
                            .background(Color.White, shape = RoundedCornerShape(8.dp)),
                    )
                }

                // Save Button
                Button(
                    onClick = {},
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                ) {
                    Text("Save")
                }
            }
        }
    }
}

