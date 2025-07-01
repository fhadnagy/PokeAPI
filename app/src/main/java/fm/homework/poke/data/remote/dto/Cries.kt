package fm.homework.poke.data.remote.dto


import com.google.gson.annotations.SerializedName

data class Cries(
    @SerializedName("latest")
    val latest: String,
    @SerializedName("legacy")
    val legacy: String
)