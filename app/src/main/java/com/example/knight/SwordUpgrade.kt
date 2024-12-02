package com.example.knight

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SwordUpgrade(upgradeSwordCost: Int){
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ){
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier
                    .size(50.dp),
                painter = painterResource(R.drawable.currency),
                contentDescription = "upgrade"
            )
            Text(
                fontSize = 20.sp,
                text = "Sword Damage"
            )
        }

        // coste de la mejora procedural
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Text(
                fontSize = 20.sp,
                text = upgradeSwordCost.toString()
            )
            Image(
                modifier = Modifier
                    .size(20.dp),
                painter = painterResource(R.drawable.currency),
                contentDescription = "cost_icon"
            )
        }
    }
}

@Preview
@Composable
fun PreviewDialog() {
    SwordUpgrade(100)
}