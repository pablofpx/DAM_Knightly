package com.example.knight

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SwordUpgrade(upgradeSwordCost: Int, changeCost: () -> Unit){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { changeCost() },
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ){
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier
                    .size(50.dp),
                painter = painterResource(R.drawable.weapon_sword),
                contentDescription = "upgrade"
            )
            Spacer(modifier = Modifier.width(16.dp))
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
    Spacer(
        modifier = Modifier.height(16.dp)
    )
}

@Composable
fun MagicUpgrade(upgradeMagicCost: Int, changeCost: () -> Unit) {
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
                painter = painterResource(R.drawable.weapon_hand),
                contentDescription = "upgrade"
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                fontSize = 20.sp,
                text = "Magic Damage"
            )
        }

        // coste de la mejora procedural
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Text(
                fontSize = 20.sp,
                text = upgradeMagicCost.toString()
            )
            Image(
                modifier = Modifier
                    .size(20.dp),
                painter = painterResource(R.drawable.currency),
                contentDescription = "cost_icon"
            )
        }
    }
    Spacer(
        modifier = Modifier.height(16.dp)
    )
}

@Preview
@Composable
fun PreviewDialog() {
    Column(
        modifier = Modifier
    ) {
        SwordUpgrade(100, changeCost ={})
        MagicUpgrade(100, changeCost = {})
    }
}