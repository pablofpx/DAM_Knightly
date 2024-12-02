package com.example.knight.core.navigation

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.internal.composableLambda
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.knight.GameScreen
import com.example.knight.Home
import com.example.knight.MainActivity
import kotlin.coroutines.coroutineContext

// esto es para manejar la navegacion

@Composable
fun NavigationWrapper() {
    val navController = rememberNavController()
    val context = LocalContext.current as Activity
    NavHost(navController = navController, startDestination = Home) {
        composable<Home>{
            Home { navController.navigate(Game) }
        }

        composable<Game> {
            // lambda para que salga directamente
            GameScreen(onExit = { context.finish() })
        }
    }
}