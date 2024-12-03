package com.example.knight.ui.theme.components

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.knight.R
import com.example.knight.SettingsDialog

@Composable
fun BottomBar() {
    var show by rememberSaveable { mutableStateOf(false) }
    val context = LocalContext.current

    BottomAppBar(
        modifier = Modifier
            .background(Color.White)
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            BottomBarItem(
                imageRes = R.drawable.sword,
                text = "Dungeon",
                onClick = { }
            )
            VerticalDivider(color = Color.LightGray, thickness = 2.dp) // está por verse
            BottomBarItem(
                imageRes = R.drawable.magicbook,
                text = "Quests",
                onClick = { toastWIP(context) }
            )
            VerticalDivider(color = Color.LightGray, thickness = 2.dp)
            BottomBarItem(
                imageRes = R.drawable.anvil,
                text = "Quests",
                onClick = { show = true }
            )
            SettingsDialog(
                show = show,
                onDismissRequest = { show = false },
                onConfirmation = { show = false }
            )
        }
    }
}

fun toastWIP(context: Context) {
    Toast.makeText(context, "Still in development...", Toast.LENGTH_SHORT).show()
}
