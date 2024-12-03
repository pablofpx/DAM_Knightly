package com.example.knight.core.navigation

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.internal.composableLambda
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.knight.GameScreen
import com.example.knight.Home
import com.example.knight.MainActivity
import com.example.knight.data.model.repository.GameRepository
import com.example.knight.data.model.repository.GameState
import kotlin.coroutines.coroutineContext

// esto es para manejar la navegacion

@Composable
fun NavigationWrapper() {
    val navController = rememberNavController()
    val context = LocalContext.current as Activity

    val gameRepository = remember { GameRepository(context)}
    val gameState by remember { mutableStateOf(gameRepository.loadGameState()) }

    NavHost(navController = navController, startDestination = Home) {
        composable<Home>{
            Home { navController.navigate(Game) }
        }

        composable<Game> {
            // lambda para que salga directamente
            GameScreen(
                gameState = gameState,
                saveGameState = { gameState ->
                    gameRepository.saveGameState(gameState)
                },
                onExit = {
                    gameRepository.saveGameState(gameState)
                    context.finish()
                }
            )
        }
    }
}