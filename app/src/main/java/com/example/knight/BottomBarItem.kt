package com.example.knight

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

// considerar hacerlos más grande o que no se vea el fundido gris al pulsar
@Composable
fun BottomBarItem(imageRes: Int, text: String, onClick: () -> Unit) {
    Button(
        modifier = Modifier
            .size(100.dp), // Tamaño cuadrado
        shape = RectangleShape, // Forma rectangular (sin bordes redondeados)
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent, // Fondo transparente
            contentColor = Color.Black), // color del contenido
        onClick = {}
    ) {
        Column(
            modifier = Modifier,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = painterResource(imageRes),
                contentDescription = text,
                modifier = Modifier
                    .fillMaxWidth()
                    .size(36.dp) // Tamaño de la imagen
            )
            // Texto
            Text(
                text = text,
                style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}