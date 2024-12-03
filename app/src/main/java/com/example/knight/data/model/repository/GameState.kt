package com.example.knight.data.model.repository

data class GameState(
    var coins: Int = 0,
    var hp: Int = 15,
    var maxHp: Int = 15,
    var currentMonsterIndex: Int = 0,
    var monsterLevel: Int = 1,
    var monsterType: String = "ghost",
    var currentWeapon: String = "", // Nombre del arma actual
    var currentDamage: Int = 0
)