package fm.homework.poke.data.remote.dto


import com.google.gson.annotations.SerializedName

data class MoveDamageClass(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
)