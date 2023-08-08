package com.example.pokedex.ui.favoritepokemon

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.pokedex.data.model.PokedexListEntry

@Composable
fun FavoritePokemonScreen(
    viewModel: FavoritePokemonViewModel = hiltViewModel()
) {
    val savedPokemonList = viewModel.savedPokemonList.value
    Box(modifier = Modifier.fillMaxWidth()) {
        if (savedPokemonList.isEmpty()) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = "Favorites",
                    tint = Color.LightGray,
                    modifier = Modifier.size(36.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "No favorites yet!",
                    fontSize = 18.sp,
                    color = Color.LightGray
                )
            }
        }
        LazyColumn {
            items(savedPokemonList) { pokemon ->
                SavedPokemonItem(pokemon = pokemon)
            }
        }
    }
}

@Composable
fun SavedPokemonItem(
    pokemon: PokedexListEntry
) {
    Row(
        modifier = Modifier.fillMaxWidth().height(50.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Text(text = "#${pokemon.number}")
            Text(text = pokemon.name)
        }
        AsyncImage(model = "", contentDescription = pokemon.name)
    }
}

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    hint: String = "",
    onSearch: (String) -> Unit = {}
) {
    var text by remember {
        mutableStateOf("")
    }
    var isHintDisplayed by remember {
        mutableStateOf(hint != "")
    }

    BasicTextField(
        value = text,
        onValueChange = {
            text = it
            onSearch(text)
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .padding(horizontal = 16.dp)
            .background(
                color = Color.LightGray,
                shape = CircleShape
            )
            .onFocusChanged {
                isHintDisplayed = !it.isFocused && text.isEmpty()
            },
        singleLine = true,
        decorationBox = {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Row(
                    modifier = Modifier.fillMaxHeight(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "",
                        tint = Color.DarkGray
                    )
                    Text(
                        text = text.ifEmpty { hint },
                        fontSize = 18.sp,
                        color = Color.DarkGray
                    )
                }
                if (text.isNotEmpty()) {
                    Icon(
                        imageVector = Icons.Default.Clear,
                        contentDescription = "Clear the text field",
                        tint = Color.DarkGray,
                        modifier = Modifier.clickable {
                            text = ""
                        }
                    )
                }
            }
        })
}