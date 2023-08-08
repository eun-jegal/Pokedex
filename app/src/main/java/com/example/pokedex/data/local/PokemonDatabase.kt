package com.example.pokedex.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.pokedex.data.model.PokedexListEntry

@Database(
    entities = [PokedexListEntry::class],
    version = 1
)
abstract class PokemonDatabase : RoomDatabase() {
    abstract val pokemonDao: PokemonDao
}