package com.example.knight

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import com.example.knight.core.navigation.Game


// molaria separar las vistas aqui pero meda pereza hacerlo ahora

@Composable
fun Home( navigateToGame: () -> Unit ) {
    Box(
        modifier = Modifier
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color(0xFF431c53),
                        Color(0xFF210e29)
                    )
                )
            )
    ) {
        Surface (
            color = Color.Transparent,
            modifier = Modifier.fillMaxSize()
        ){
            // serias dudas de si poner opciones
            Column (
                modifier = Modifier
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Column (
                        modifier = Modifier
                            .fillMaxHeight(0.5f),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ){
                        Text(
                            text = "Knightly",
                            fontSize = 55.sp,
                            fontFamily = FontFamily.SansSerif,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                        Text(
                            modifier = Modifier
                                .padding(16.dp),
                            text = "Tap, tap, tap...",
                            fontSize = 20.sp,
                            fontFamily = FontFamily.Monospace,
                            color = Color.White
                        )

                        Spacer(modifier = Modifier.height(100.dp) )

                        // boton para pasar de screen
                        Button(
                            onClick = { navigateToGame()},
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.Transparent,
                                contentColor = Color.White
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(100.dp)
                        ) {
                            Text(
                                text = "Tap to start!",
                                fontSize = 16.sp,
                                fontFamily = FontFamily.Serif
                            )
                        }
                    }
                }
                Column (
                    modifier = Modifier
                        .fillMaxHeight(0.25f),
                    horizontalAlignment = Alignment.End,
                    verticalArrangement = Arrangement.Bottom
                ){
                    Row (
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ){
                        Text(
                            modifier = Modifier,
                            text = "A game by Pablo",
                            fontSize = 16.sp,
                            color = Color.White
                        )
                    }
                }
            }
        }

    }

}