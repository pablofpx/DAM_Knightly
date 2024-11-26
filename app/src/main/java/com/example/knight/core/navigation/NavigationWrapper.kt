package com.example.knight.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.internal.composableLambda
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.knight.GameScreen
import com.example.knight.HomeScreen
import com.example.knight.MainActivity

// esto es para manejar la navegacion

@Composable
fun NavigationWrapper() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Home) {
        composable<Home>{
            HomeScreen { navController.navigate(Game)}
        }

        composable<Game> {
            GameScreen()
        }
    }
}