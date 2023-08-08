package com.example.pokedex.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "pokemons"
)
data class PokedexListEntry(
    val name: String,
    val imageUrl: String,
    @PrimaryKey
    val number: Int
)