package fm.homework.poke.data.remote.dto


import com.google.gson.annotations.SerializedName

data class PokemonListDTO(
    @SerializedName("count")
    val count: Int,
    @SerializedName("next")
    val nextPage: String?,
    @SerializedName("previous")
    val previousPage: String?,
    @SerializedName("results")
    val results: List<ResultX>
)