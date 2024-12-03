package com.example.knight

import android.content.Context
import android.os.Bundle
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
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.knight.core.navigation.NavigationWrapper
import com.example.knight.data.model.repository.GameRepository
import com.example.knight.data.model.repository.GameState
import com.example.knight.ui.theme.components.BottomBarItem
import com.example.knight.ui.theme.components.Monster
import com.example.knight.ui.theme.components.TopMenu
import com.example.knight.utils.triggerHPScaleAnimation
import com.example.knight.utils.triggerShakeAnimation
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private lateinit var gameRepository: GameRepository
    private lateinit var gameState: GameState

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
    val mockGameState = GameState(
        coins = 0,
        hp = 15,
        maxHp = 15,
        currentMonsterIndex = 0,
        monsterLevel = 1,
        monsterType = "ghost"
    )
    // Simulación de guardar estado
    val saveGameState: (GameState) -> Unit = { updatedState ->
        println("Simulated save: $updatedState")
    }

    // Simulación de la salida del juego
    val onExit: () -> Unit = {
        println("Simulated exit")
    }

    // Renderizamos GameScreen con el estado y funciones simuladas
    GameScreen(
        gameState = mockGameState,
        saveGameState = saveGameState,
        onExit = onExit
    )
}

// juego
@Composable
fun GameScreen(
    gameState: GameState,
    saveGameState: (GameState) -> Unit,
    onExit: () -> Unit
) {
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
            Content(
                gameState = gameState,
                saveGameState = saveGameState,
                modifier = Modifier.padding(paddingValues)
            )
        },
        bottomBar = {
            BottomBar()
        }
    )

}

// una variable que varía en función del bicho (va escalando etc)
// fun Monster(string: String) : Int { // depende de la imagen del bicho


@Composable
fun Content(
    gameState: GameState,
    saveGameState: (GameState) -> Unit,
    modifier: Modifier
) {

    /* Animaciones
    val coroutineScope = rememberCoroutineScope()
    val shakeOffset = remember { Animatable(0f) }
    val hpScale = remember { Animatable(1f) }

     */

    val currentMonster = when (gameState.currentMonsterIndex) {
        0 -> R.drawable.physical_pakman
        1 -> R.drawable.ghost_star
        2 -> R.drawable.ghost_squid
        else -> R.drawable.ghost_squid
    }

    // Función para el ataque al monstruo
    fun handleAttack() {
        val updatedGameState = if (gameState.hp > 2) {
            gameState.copy(hp = gameState.hp - 2)
        } else {
            val newLevel = gameState.monsterLevel + 1
            val newMaxHp = 15 + (newLevel * 5)
            val randomCoins = (2..5).random()
            gameState.copy(
                hp = newMaxHp,
                maxHp = newMaxHp,
                monsterLevel = newLevel,
                currentMonsterIndex = (gameState.currentMonsterIndex + 1) % 2,
                coins = gameState.coins + randomCoins
            )
        }
        saveGameState(updatedGameState)
    }

    Column(
        modifier = modifier
            .background(Color.LightGray)
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        // Menú superior con las monedas
        TopMenu(gameState.coins)

        Spacer(modifier = Modifier.height(16.dp))

        // Usamos el composable Monster
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(.9f),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Monster(
                hp = gameState.hp,
                maxHp = gameState.maxHp,
                imageRes = currentMonster,
                onAttack = ::handleAttack
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Mostrar las armas
            val weapon = if (gameState.currentMonsterIndex % 2 == 0) {
                R.drawable.weapon_sword
            } else {
                R.drawable.weapon_hand
            }
        }
    }
}


//  no lo voy a usar porque no hace falta
val interactionSource = MutableInteractionSource()

/*
@Composable // constraint no usada
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
*/


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
                onConfirmation = { show = false }
            )
        }
    }
}

fun toastWIP(context: Context) {
    Toast.makeText(context, "Still in development...", Toast.LENGTH_SHORT).show()
}
