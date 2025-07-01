package fm.homework.poke.data.remote.dto


import com.google.gson.annotations.SerializedName
import fm.homework.poke.domain.model.PokemonDetails

data class PokemonX(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
)

fun PokemonX.toPokemonDetails(type: String, caught: Boolean): PokemonDetails {
    return PokemonDetails(name, listOf(), listOf(type), "", 0, 0, caught)
}