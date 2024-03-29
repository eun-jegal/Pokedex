package com.example.pokedex.ui.pokemondetail

import androidx.lifecycle.ViewModel
import com.example.pokedex.data.remote.responses.Pokemon
import com.example.pokedex.data.repository.PokemonRepository
import com.example.pokedex.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PokemonDetailViewModel @Inject constructor(
    private val repository: PokemonRepository
): ViewModel() {

    suspend fun getPokemonDetail(name: String): Resource<Pokemon> {
        return repository.getPokemonDetail(name)
    }
}