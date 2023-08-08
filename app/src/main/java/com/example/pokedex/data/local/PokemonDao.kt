package com.example.pokedex.data.local

import androidx.room.*
import com.example.pokedex.data.remote.responses.Pokemon
import kotlinx.coroutines.flow.Flow

@Dao
interface PokemonDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun savePokemon(pokemon: Pokemon)

    @Delete
    suspend fun deletePokemon(pokemon: Pokemon)

    @Query("SELECT * FROM pokemons")
    fun getSavedPokemonList(): Flow<List<Pokemon>>
}