package com.example.pokedex.util

import androidx.compose.ui.graphics.Color
import com.example.pokedex.R
import com.example.pokedex.data.remote.responses.Stat
import com.example.pokedex.data.remote.responses.Type
import com.example.pokedex.ui.theme.*
import java.util.*

fun parseTypeToColor(type: Type): Color {
    return when(type.type.name.toLowerCase(Locale.ROOT)) {
        "normal" -> TypeNormal
        "fire" -> TypeFire
        "water" -> TypeWater
        "electric" -> TypeElectric
        "grass" -> TypeGrass
        "ice" -> TypeIce
        "fighting" -> TypeFighting
        "poison" -> TypePoison
        "ground" -> TypeGround
        "flying" -> TypeFlying
        "psychic" -> TypePsychic
        "bug" -> TypeBug
        "rock" -> TypeRock
        "ghost" -> TypeGhost
        "dragon" -> TypeDragon
        "dark" -> TypeDark
        "steel" -> TypeSteel
        "fairy" -> TypeFairy
        else -> Color.Black
    }
}

fun parseTypeToIcon(type: Type): Int {
    return when(type.type.name.toLowerCase(Locale.ROOT)) {
        "fire" -> R.drawable.ic_fire
        "water" -> R.drawable.ic_water
        "electric" -> R.drawable.ic_electric
        "grass" -> R.drawable.ic_grass
        "ice" -> R.drawable.ic_ice
        "fighting" -> R.drawable.ic_fighting
        "poison" -> R.drawable.ic_poison
        "ground" -> R.drawable.ic_ground
        "flying" -> R.drawable.ic_flying
        "psychic" -> R.drawable.ic_psychic
        "bug" -> R.drawable.ic_bug
        "rock" -> R.drawable.ic_rock
        "ghost" -> R.drawable.ic_ghost
        "dragon" -> R.drawable.ic_dragon
        "dark" -> R.drawable.ic_dark
        "steel" -> R.drawable.ic_steel
        "fairy" -> R.drawable.ic_fairy
        else -> R.drawable.ic_normal
    }
}

fun parseStatToColor(stat: Stat): Color {
    return when(stat.stat.name.toLowerCase()) {
        "hp" -> HPColor
        "attack" -> AtkColor
        "defense" -> DefColor
        "special-attack" -> SpAtkColor
        "special-defense" -> SpDefColor
        "speed" -> SpdColor
        else -> Color.White
    }
}

fun parseStatToAbbr(stat: Stat): String {
    return when(stat.stat.name.toLowerCase()) {
        "hp" -> "HP"
        "attack" -> "Atk"
        "defense" -> "Def"
        "special-attack" -> "SpAtk"
        "special-defense" -> "SpDef"
        "speed" -> "Spd"
        else -> ""
    }
}

fun reformatNum(num: Int): String {
    val numToString = num.toString()
    return when (num.toString().length) {
        1 -> "#00$numToString"
        2 -> "#0$numToString"
        else -> "3$numToString"
    }
}