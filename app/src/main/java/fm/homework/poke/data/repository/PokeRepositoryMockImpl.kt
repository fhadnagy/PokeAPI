package fm.homework.poke.data.repository

import fm.homework.poke.domain.model.PokeItemData
import fm.homework.poke.domain.model.PokemonDetails
import fm.homework.poke.domain.model.TypeModel
import fm.homework.poke.domain.repository.PokeRepository

class PokeRepositoryMockImpl : PokeRepository {
    override suspend fun getAllTypes(): List<TypeModel> {
        return listOf(TypeModel("Fire"), TypeModel("Water"), TypeModel("Wind"), TypeModel("Earth"))
    }

    override suspend fun getAllPokemons(): List<PokeItemData> {
        return listOf(PokeItemData("Charizard", listOf("fire","Flame","Hellfire"),true),
            PokeItemData("trex", listOf("earth","Flame","Hellfire"),true),
            PokeItemData("mole", listOf("earth","Flame","Hellfire"),true),
            PokeItemData("unicorn", listOf("water","Flame","Hellfire"),true),)
    }

    override suspend fun getPokemonsByType(type: String): List<PokeItemData> {
        return listOf(PokeItemData("Charizard", listOf("fire","Flame","Hellfire"),true),
            PokeItemData("trex", listOf("earth","Flame","Hellfire"),true),
            PokeItemData("mole", listOf("earth","Flame","Hellfire"),true),
            PokeItemData("unicorn", listOf("water","Flame","Hellfire"),true),).filter { it.types.contains(type) }
    }

    override suspend fun getPokemon(name: String): PokemonDetails {
        ///TODO: implement for 3 distinct
        return  PokemonDetails("Charizard", listOf("Burn","Flame","Hellfire"), listOf("Water","Magma"), "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/215.png",99,16,false)
    }
}