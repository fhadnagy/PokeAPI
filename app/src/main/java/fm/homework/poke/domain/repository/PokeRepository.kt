package fm.homework.poke.domain.repository

import fm.homework.poke.domain.model.PokeItemData
import fm.homework.poke.domain.model.PokemonDetails
import fm.homework.poke.domain.model.TypeModel

interface PokeRepository {
    suspend fun getAllTypes(): List<TypeModel>
    suspend fun getAllPokemons(): List<PokeItemData>
    suspend fun getPokemonsByType(type: String): List<PokeItemData>
    suspend fun getPokemon(name: String): PokemonDetails
}