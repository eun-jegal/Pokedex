package com.example.pokedex.ui.favoritepokemon

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedex.data.model.PokedexListEntry
import com.example.pokedex.data.remote.responses.Pokemon
import com.example.pokedex.data.repository.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritePokemonViewModel @Inject constructor(
    private val repository: PokemonRepository
): ViewModel() {
    private val _savedPokemonList = mutableStateOf<List<Pokemon>>(emptyList())
    val savedPokemonList: State<List<Pokemon>> = _savedPokemonList

    fun savePokemon(pokemon: PokedexListEntry) {
        viewModelScope.launch {
            repository.savePokemon(pokemon)
        }
    }

    fun deletePokemon(pokemon: PokedexListEntry) {
        viewModelScope.launch {
            repository.deletePokemon(pokemon)
        }
    }
}