package fm.homework.poke.data.remote.dto


import com.google.gson.annotations.SerializedName
import fm.homework.poke.domain.model.PokeItemData
import fm.homework.poke.domain.model.PokemonDetails

data class PokemonX(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
)

fun PokemonX.toPokeItemData(type: String,  caught: Boolean): PokeItemData {
    return PokeItemData(name,  listOf(type),  caught)
}