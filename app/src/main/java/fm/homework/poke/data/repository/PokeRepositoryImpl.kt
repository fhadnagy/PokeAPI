package fm.homework.poke.data.repository

import fm.homework.poke.data.remote.PokeAPI
import fm.homework.poke.domain.model.Pokemon
import fm.homework.poke.domain.model.Type
import fm.homework.poke.domain.repository.PokeRepository
import javax.inject.Inject

class PokeRepositoryImpl @Inject constructor(
    private val api: PokeAPI
) : PokeRepository {
    override suspend fun getAllTypes(): List<Type> {
        TODO("Not yet implemented")
    }

    override suspend fun getAllPokemons(): List<Pokemon> {
        TODO("Not yet implemented")
    }

    override suspend fun getPokemonsByType(type: String): List<Pokemon> {
        TODO("Not yet implemented")
    }

    override suspend fun getPokemon(name: String): Pokemon {
        TODO("Not yet implemented")
    }
}