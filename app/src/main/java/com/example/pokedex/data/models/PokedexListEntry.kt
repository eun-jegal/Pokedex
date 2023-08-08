package com.example.pokedex.data.models

import androidx.room.Entity

@Entity(
    tableName = "pokemons"
)
data class PokedexListEntry(
    val name: String,
    val imageUrl: String,
    val number: Int
)