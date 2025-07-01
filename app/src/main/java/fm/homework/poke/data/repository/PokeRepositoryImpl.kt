package fm.homework.poke.data.repository

import fm.homework.poke.data.remote.PokeAPI
import fm.homework.poke.data.remote.dto.toPokemonDetails
import fm.homework.poke.data.remote.dto.toTypeModel
import fm.homework.poke.domain.model.PokemonDetails
import fm.homework.poke.domain.model.TypeModel
import fm.homework.poke.domain.repository.PokeRepository
import javax.inject.Inject

class PokeRepositoryImpl @Inject constructor(
    private val api: PokeAPI
) : PokeRepository {
    //TODO: merging of caught or uncaught and saving to local db and returning happens here
    override suspend fun getAllTypes(): List<TypeModel> {
        return api.getAllTypes().results.map { it.toTypeModel() }
    }

    override suspend fun getAllPokemons(): List<PokemonDetails> {
        val response = api.getAllPokemons().results
        //should be based on local db
        return response.map { it.toPokemonDetails("", false) }
    }

    override suspend fun getPokemonsByType(type: String): List<PokemonDetails> {
        val response = api.getPokemonByType(type).pokemon
        //should be based on local db
        return response.map { it.pokemon.toPokemonDetails(type, false) }
    }

    override suspend fun getPokemon(name: String): PokemonDetails {
        TODO("Not yet implemented")
    }
}