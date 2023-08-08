package com.example.pokedex.data.local

import androidx.room.Database
import com.example.pokedex.data.models.PokedexListEntry

@Database(
    entities = [PokedexListEntry::class],
    version = 1
)
abstract class PokemonDatabase {
    abstract val pokemonDao: PokemonDao
}