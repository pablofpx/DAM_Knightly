package com.example.knight.ui.theme.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.knight.R

@Composable
fun TopMenu(coins: Int) {
    Box (
        modifier = Modifier
            .padding(top = 30.dp)
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ){
        Row (
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            Row (
                verticalAlignment = Alignment.CenterVertically
            ){
                Image(
                    painter = painterResource(R.drawable.currency),
                    contentDescription = "coins",
                    modifier = Modifier
                        .size(30.dp)
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    modifier = Modifier,
                    text = coins.toString(),
                    fontSize = 20.sp,
                    color = colorResource(id = R.color.coin_blue)
                )
            }

            IconButton(
                onClick = {  }
            ) {
                Icon(
                    painter = painterResource(R.drawable.homescreen_settings),
                    contentDescription = "Settings",
                    tint = Color.Black,
                    modifier = Modifier.size(35.dp)
                )
            }
        }
    }
}