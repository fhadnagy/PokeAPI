package fm.homework.poke.data.repository

import android.util.Log
import fm.homework.poke.data.remote.PokeAPI
import fm.homework.poke.data.remote.dto.toPokeItemData
import fm.homework.poke.data.remote.dto.toTypeModel
import fm.homework.poke.domain.model.PokeItemData
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

    override suspend fun getAllPokemons(): List<PokeItemData> {
        val types = getAllTypes()
        val pokes = mutableListOf<PokeItemData>()
        for (type in types) {
            val dto = api.getPokemonByType(type.name).pokemon
            dto.forEach {
                if (it.slot == 1)
                    pokes.add(it.pokemon.toPokeItemData(type.name, false))
            }
        }
        Log.d("PokeRepositoryImpl", "getAllPokemons finished")
        return pokes.toList()
    }
    override suspend fun getPokemonsByType(type: String): List<PokeItemData> {
        val response = api.getPokemonByType(type).pokemon
        //should be based on local db
        return response.filter{it.slot==1}.map { it.pokemon.toPokeItemData(type, false) }
    }

    override suspend fun getPokemon(name: String): PokemonDetails {
        val response = api.getPokemonDetail(name)
        return PokemonDetails(response.name, response.abilities.filter { !it.isHidden }.map { it.ability.name }, response.types.map { it.type.name }, response.sprites.frontDefault, response.height, response.weight, false)
    }
}