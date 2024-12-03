package com.example.knight.data.model

// por ahora no voy a hacer settings

data class GameData(
    val player: Player,
    val monster: Monster,
    val progress: Progress
)

data class Player(
    val coins: Int,
    val weapons: Map<String, Weapon>,
    val currentWeapon: String
)

data class Weapon(
    val baseDamage: Int,
    val upgrades: Int,
    val currentDamage: Int,
    val upgradeCost: Int
)

data class Monster(
    val currentHp: Int,
    val maxHp: Int,
    val coinsReward: List<Int>,
    val lastMonsterType: String  // guardar tipo de monstruo
)

data class Progress(
    val totalMonstersDefeated: Int = 0
)