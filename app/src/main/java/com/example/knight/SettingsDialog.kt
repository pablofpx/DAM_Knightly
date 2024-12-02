package com.example.knight

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.TextButton
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalMapOf
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter


@Composable
fun SettingsDialog(
    show: Boolean,
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit
) {
    var upgradeSwordCost by remember { mutableStateOf(50) }
    var upgradeMagicCost by remember { mutableStateOf(50) }
    if(show){
        Dialog(onDismissRequest = { onDismissRequest() }) {
            Card(
                modifier = Modifier,
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Upgrades",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    // lo mejor sería recorrer una lista de funciones
                    LazyColumn (
                        modifier = Modifier
                            .height(200.dp)
                            .fillMaxWidth()
                    ){
                        item{
                            SwordUpgrade(upgradeSwordCost = upgradeSwordCost,
                                changeCost = { upgradeSwordCost+=100 }
                            )
                        }
                        item {
                            MagicUpgrade(upgradeMagicCost = upgradeMagicCost,
                                changeCost = { upgradeMagicCost+=100 })
                        }
                    }

                    Row (
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ){
                        TextButton(onClick = { onDismissRequest() }) {
                            Text(text = "Cerrar")
                        }
                        TextButton(onClick = { onConfirmation()}) {
                            Text(text = "Confirmar")
                        }
                    }
                }
            }
        }

    }
}


@Preview(showBackground = true)
@Composable
fun Preview(){
    SettingsDialog(
        show = true,
        onDismissRequest = {},
        onConfirmation = {}
    )
}

