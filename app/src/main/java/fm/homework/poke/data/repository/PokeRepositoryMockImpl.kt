package fm.homework.poke.data.repository

import fm.homework.poke.domain.model.Pokemon
import fm.homework.poke.domain.model.Type
import fm.homework.poke.domain.repository.PokeRepository

class PokeRepositoryMockImpl : PokeRepository {
    override suspend fun getAllTypes(): List<Type> {
        return listOf(Type("Fire", 1), Type("Water", 2), Type("Wind", 3), Type("Earth", 4))
    }

    override suspend fun getAllPokemons(): List<Pokemon> {
        return listOf(Pokemon("Charizard", listOf("Burn","Flame","Hellfire"), listOf("Fire","Magma"), "",15,16,false),
            Pokemon("Charizard", listOf("Burn","Flame","Hellfire"), listOf("Fire","Magma"), "",15,16,false),
            Pokemon("Charizard", listOf("Burn","Flame","Hellfire"), listOf("Fire","Magma"), "",15,16,true),
            Pokemon("Charizard", listOf("Burn","Flame","Hellfire"), listOf("Water","Magma"), "",15,16,true),
            Pokemon("Charizard", listOf("Burn","Flame","Hellfire"), listOf("Fire","Magma"), "",15,16,false))
    }

    override suspend fun getPokemonsByType(type: String): List<Pokemon> {
        ///TODO: implement for water, fire, earth, wind and other
        return listOf(Pokemon("Charizard", listOf("Burn","Flame","Hellfire"), listOf("Water","Magma"), "",15,16,false),
            Pokemon("Charizard", listOf("Burn","Flame","Hellfire"), listOf("Water","Magma"), "",15,16,true),
            Pokemon("Charizard", listOf("Burn","Flame","Hellfire"), listOf("Water","Magma"), "",15,16,false))
    }

    override suspend fun getPokemon(name: String): Pokemon {
        ///TODO: implement for 3 distinct
        return  Pokemon("Charizard", listOf("Burn","Flame","Hellfire"), listOf("Water","Magma"), "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/215.png",99,16,false)
    }
}