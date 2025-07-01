package fm.homework.poke.data.remote.dto


import com.google.gson.annotations.SerializedName

data class TypeListDTO(
    @SerializedName("count")
    val count: Int,
    @SerializedName("next")
    val next: String,
    @SerializedName("previous")
    val previous: Any?,
    @SerializedName("results")
    val results: List<Result>
)