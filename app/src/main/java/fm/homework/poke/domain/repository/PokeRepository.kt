package fm.homework.poke.domain.repository

import fm.homework.poke.domain.model.Pokemon
import fm.homework.poke.domain.model.Type

interface PokeRepository {
    suspend fun getAllTypes(): List<Type>
    suspend fun getAllPokemons(): List<Pokemon>
    suspend fun getPokemonsByType(type: String): List<Pokemon>
    suspend fun getPokemon(name: String): Pokemon
}