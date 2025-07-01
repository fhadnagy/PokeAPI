package fm.homework.poke.data.remote.dto


import com.google.gson.annotations.SerializedName
import fm.homework.poke.domain.model.TypeModel

data class Result(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
)

fun Result.toTypeModel(): TypeModel {
    return TypeModel(name)
}