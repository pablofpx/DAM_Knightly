package com.example.knight

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
fun Home(navigateToGame: () -> Unit) {
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
            Row (
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 35.dp),
                horizontalArrangement = Arrangement.End
            ){
                Button(
                    onClick = { },
                    modifier = Modifier,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent,
                        contentColor = Color.White
                    ),
                ) {
                    Image(
                        painter = painterResource(R.drawable.homescreen_settings),
                        contentDescription = "Opciones home",
                        modifier = Modifier.size(35.dp),
                        contentScale = ContentScale.Fit
                    )
                }
            }
            Column (
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Knightly",
                    fontSize = 50.sp,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )

                Spacer(modifier = Modifier.height(100.dp) )

                // boton para pasar de screen
                Button(
                    onClick = { navigateToGame() },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent,
                        contentColor = Color.White
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp)
                ) {
                    Text(
                        text = "Pulsa para comenzar",
                        fontSize = 16.sp,
                        fontFamily = FontFamily.Serif
                    )
                }
            }
        }

    }

}