package com.example.knight.data.model.repository

import android.content.Context
import com.google.gson.Gson

class GameRepository(private val context: Context) {

    private val sharedPreferences = context.getSharedPreferences("game_data", Context.MODE_PRIVATE)
    private val gson = Gson()

    fun saveGameState(state: GameState) {
        val json = gson.toJson(state)
        sharedPreferences.edit().putString("game_state", json).apply()
    }

    fun loadGameState(): GameState {
        val json = sharedPreferences.getString("game_state", null)
        return if (json != null) {
            gson.fromJson(json, GameState::class.java)  // Convertir JSON de vuelta a GameState
        } else {
            GameState(monsterType = "ghost")  // Estado predeterminado si no hay datos guardados
        }
    }

    fun deleteGameData() {
        sharedPreferences.edit().clear().apply()
    }
}