package com.example.pokedex.data.repository

import com.example.pokedex.data.model.PokedexListEntry
import com.example.pokedex.data.remote.responses.Pokemon
import com.example.pokedex.data.remote.responses.PokemonList
import com.example.pokedex.util.Resource
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {
    suspend fun getPokemonList(limit: Int, offset: Int): Resource<PokemonList>
    suspend fun getPokemonDetail(name: String): Resource<Pokemon>
    suspend fun savePokemon(pokemon: PokedexListEntry)
    suspend fun deletePokemon(pokemon: PokedexListEntry)
    fun getSavedPokemonList(): Flow<List<PokedexListEntry>>
}