package fm.homework.poke.data.remote.dto


import com.google.gson.annotations.SerializedName

data class DoubleDamageFrom(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
)