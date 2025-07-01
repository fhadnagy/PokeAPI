package fm.homework.poke.presentation.details

import fm.homework.poke.domain.model.PokemonDetails

data class DetailsState(
    val isLoading: Boolean = false,
    val pokemonDetails: PokemonDetails? = null,
    val error: String = ""
)