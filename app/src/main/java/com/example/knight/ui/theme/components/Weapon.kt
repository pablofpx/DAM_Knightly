package com.example.knight.ui.theme.components

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun Weapon(name: String, damage: Int, upgrades: Int){
    Row {
        Text("Weapon: $name")
        Text("Damage: $damage")
        Text("Upgrades: $upgrades")
    }
}