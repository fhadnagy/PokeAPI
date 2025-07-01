package fm.homework.poke.presentation.details

import fm.homework.poke.domain.model.Pokemon

data class DetailsScreenState(
    val isLoading: Boolean = false,
    val pokemonDetails: Pokemon? = null,
    val error: String? = null
)