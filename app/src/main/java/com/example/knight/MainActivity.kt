package com.example.knight

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.compose.rememberNavController
import com.example.knight.core.navigation.Game
import com.example.knight.core.navigation.NavigationWrapper
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NavigationWrapper()
        }
    }
}


@Preview
@Composable
fun GameScreenPreview() {
    GameScreen()
}

// juego
@Composable
fun GameScreen(onExit: () -> Unit = {}) {
    var showExitConfirmation by remember { mutableStateOf(false) }

    BackHandler {
        showExitConfirmation = true // dialogo de confirmacion
    }

    // trigger del dialogo
    if (showExitConfirmation) {
        AlertDialog(
            onDismissRequest = { showExitConfirmation = false },
            title = { Text("Exit game?") },
            text = { Text("Are you sure you want to leave?") },
            confirmButton = {
                TextButton(onClick = { onExit() }) {
                    Text("Exit")
                }
            },
            dismissButton = {
                TextButton(onClick = { showExitConfirmation = false }) {
                    Text("Cancel")
                }
            }
        )
    }
    // la idea es hacer una barra bonita arriba con las monedas
    // en el centro el monigote
    // y abajo un botón para pulsar y las quests para ganar más monedas
    Scaffold (
        topBar = { },
        content = { paddingValues ->
            Content(modifier = Modifier.padding(paddingValues))
        },
        bottomBar = {
            BottomBar()
        }
    )

}

// una variable que varía en función del bicho (va escalando etc)
// fun Monster(string: String) : Int { // depende de la imagen del bicho


@Composable
fun Content(modifier: Modifier) {
    var monsters = listOf(
        R.drawable.physical_pakman,
        R.drawable.ghost_star,
        R.drawable.ghost_squid
    )

    var hpInitial = 15;
    var hp by remember { mutableStateOf(15) }
    var currentMonsterIndex by remember { mutableStateOf(0)}
    val currentMonster = monsters[currentMonsterIndex]
    var coins by remember { mutableStateOf(0) }

    //animationss
    val shakeOffset = remember { Animatable(0f) }
    val hpScale = remember { Animatable(1f, Float.VectorConverter) }

    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .background(Color.LightGray)
            .fillMaxSize()
            .padding(16.dp), // dudas de si llenar o no toda la pantalla
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        // menu de monedas, etc
        TopMenu(coins)

        Spacer(modifier = Modifier.height(16.dp))

        // monstruo // la idea es que haya que tapear para bajarle la vida
        // cuando muera recibes una cantidad de oro random
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .clickable(interactionSource = remember { MutableInteractionSource() },
                    indication = null
                ) {
                    // queda pendiente hacer escalado de vida
                    hp -= 2 // el daño va a variar en función del arma
                    if (hp <= 0) {
                        val randomIncrement = (2..5).random()

                        val newMaxHp = (hpInitial + randomIncrement).coerceAtMost(60)
                        hp = newMaxHp
                        hpInitial = newMaxHp
                        // si muere escoge otro
                        // reinicia su vida pero depende del monstruo (WIP)
                        currentMonsterIndex = (monsters.indices).random()
                        // ganar monedas
                        coins+= (2..4).random()
                    }

                    coroutineScope.launch {
                        triggerShakeAnimation(shakeOffset)
                        triggerHPScaleAnimation(hpScale)
                    }
                },
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // hp del bicho
                Text(
                    modifier = Modifier
                        .graphicsLayer(scaleX = hpScale.value, scaleY = hpScale.value)
                        .padding(bottom = 16.dp),
                    text = "HP: $hp",
                    fontSize = 30.sp,
                    color = if (hp > 7) Color.Black else Color.Red
                )

                // bicho que varía aleatoriamente de imagen
                Image(
                    painter = painterResource(currentMonster),
                    contentDescription = "Monster",
                    modifier = Modifier
                        .size(300.dp)
                        .offset { IntOffset(shakeOffset.value.toInt(), 0) }
                )
            }
            // mensaje para incentivar a pulsar
            Text(
                modifier = Modifier
                    .align(Alignment.BottomCenter),
                text = "Attack!",
                fontSize = 30.sp
            )
        }

        Spacer(modifier = Modifier.height(16.dp))
        
        // espada y magia
        var alignment: Alignment
        val weapon: Int

        if (currentMonsterIndex % 2 == 0) {
            weapon = R.drawable.weapon_sword
            alignment = Alignment.TopEnd
        } else {
            weapon = R.drawable.weapon_hand
            alignment = Alignment.TopStart
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp), // Ajustar el tamaño de la espada
            contentAlignment = alignment
        ) {
            Row {
                Image(
                    painter = painterResource(weapon),
                    contentDescription = "weapons",
                    modifier = Modifier
                        .fillMaxHeight(0.8f)
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
    }

}


// funciones para animar los efectos
suspend fun triggerShakeAnimation(shakeOffset: Animatable<Float, AnimationVector1D>) {
    shakeOffset.animateTo(
        targetValue = 20f,
        animationSpec = tween(durationMillis = 50)
    )
    shakeOffset.animateTo(
        targetValue = -20f,
        animationSpec = tween(durationMillis = 50)
    )
    shakeOffset.animateTo(
        targetValue = 0f,
        animationSpec = tween(durationMillis = 50)
    )
}

suspend fun triggerHPScaleAnimation(hpScale: Animatable<Float, AnimationVector1D>) {
    hpScale.animateTo(
        targetValue = 1.3f,
        animationSpec = tween(durationMillis = 50)
    )
    hpScale.animateTo(
        targetValue = 1f,
        animationSpec = tween(durationMillis = 50)
    )
}

//  no lo voy a usar porque no hace falta
val interactionSource = MutableInteractionSource()


@Composable
fun ConstraintContent(modifier: Modifier = Modifier) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
    ) {
        val (screen) = createRefs()
        Box (
          modifier = Modifier
              .constrainAs(screen) {
                  top.linkTo(parent.top)
                  start.linkTo(parent.start)
                  end.linkTo(parent.end)
                  bottom.linkTo(parent.bottom)
              }
              .background(Color.Red)
              .fillMaxSize()
              .clickable (interactionSource = interactionSource, indication = null){  } // elimina el efecto
        ) {
        }
    }
}

// shape = RoundedCornerShape(50.dp)
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
                onConfirmation = {
                    show = false
                }
            )
        }
    }
}

fun toastWIP(context: Context) {
    Toast.makeText(context, "Still in development...", Toast.LENGTH_SHORT).show()
}
