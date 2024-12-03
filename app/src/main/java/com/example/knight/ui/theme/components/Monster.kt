package com.example.knight.ui.theme.components

import android.graphics.drawable.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.knight.core.navigation.Game
import com.example.knight.data.model.Monster
import com.example.knight.data.model.repository.GameRepository
import com.example.knight.data.model.repository.GameState


@Composable
fun Monster(
    hp: Int,
    maxHp: Int,
    imageRes: Int,
    onAttack: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onAttack() },
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Muestra la vida del monstruo
            Text(
                text = "HP: $hp / $maxHp",
                fontSize = 30.sp,
                color = if (hp > maxHp / 3) Color.Black else Color.Red
            )

            // Imagen del monstruo
            Image(
                painter = painterResource(imageRes),
                contentDescription = "Monster",
                modifier = Modifier
                    .size(300.dp)
            )
            Spacer(modifier = Modifier.height(20.dp))
            // Mensaje de "Atacar"
            Text(
                modifier = Modifier,
                text = "Attack!",
                fontSize = 30.sp
            )
        }


    }
}
        /*
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


    if (gameState.hp <= 0) {
        val newLevel = gameState.monsterLevel + 1
        val newMaxHp = 15 + (newLevel * 5) // Escala con el nivel
        val randomCoins = (2..5).random()

        // Actualizamos el estado del juego
        val updatedGameState = gameState.copy(
            hp = newMaxHp,
            maxHp = newMaxHp,
            monsterLevel = newLevel,
            currentMonsterIndex = (gameState.currentMonsterIndex + 1) % 2, // Cambio de monstruo (suponiendo 2 tipos)
            coins = gameState.coins + randomCoins
        )

        // Guardar el nuevo estado
        saveGameState(updatedGameState)


         */