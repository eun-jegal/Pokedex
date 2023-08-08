package com.example.pokedex.data.repository

import com.example.pokedex.data.local.PokemonDao
import com.example.pokedex.data.model.PokedexListEntry
import com.example.pokedex.data.remote.PokeApi
import com.example.pokedex.data.remote.responses.Pokemon
import com.example.pokedex.data.remote.responses.PokemonList
import com.example.pokedex.util.Resource
import dagger.hilt.android.scopes.ActivityScoped
import kotlinx.coroutines.flow.Flow
import java.lang.Exception
import javax.inject.Inject

@ActivityScoped
class PokemonRepositoryImpl @Inject constructor(
    private val api: PokeApi,
    private val dao: PokemonDao
) : PokemonRepository {
    override suspend fun getPokemonList(limit: Int, offset: Int): Resource<PokemonList> {
        val response = try {
            api.getPokemonList(limit, offset)
        } catch (e: Exception) {
            return Resource.Error("Unknown error occurred")
        }
        return Resource.Success(response)
    }

    override suspend fun getPokemonDetail(name: String): Resource<Pokemon> {
        val response = try {
            api.getPokemonDetail(name)
        } catch (e: Exception) {
            return Resource.Error("Unknown error occurred")
        }
        return Resource.Success(response)
    }

    override suspend fun savePokemon(pokemon: PokedexListEntry) {
        dao.savePokemon(pokemon)
    }

    override suspend fun deletePokemon(pokemon: PokedexListEntry) {
        dao.deletePokemon(pokemon)
    }

    override fun getSavedPokemonList(): Flow<List<PokedexListEntry>> {
        return dao.getSavedPokemonList()
    }
}