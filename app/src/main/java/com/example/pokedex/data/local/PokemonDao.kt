package com.example.pokedex.data.local

import androidx.room.*
import com.example.pokedex.data.model.PokedexListEntry
import kotlinx.coroutines.flow.Flow

@Dao
interface PokemonDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun savePokemon(pokemon: PokedexListEntry)

    @Delete
    suspend fun deletePokemon(pokemon: PokedexListEntry)

    @Query("SELECT * FROM pokemons")
    fun getSavedPokemonList(): Flow<List<PokedexListEntry>>
}