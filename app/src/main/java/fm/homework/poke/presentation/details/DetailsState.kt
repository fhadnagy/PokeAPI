package fm.homework.poke.presentation.details

import fm.homework.poke.domain.model.Pokemon

data class DetailsState(
    val isLoading: Boolean = false,
    val pokemonDetails: Pokemon? = null,
    val error: String = ""
)