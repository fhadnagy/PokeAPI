package fm.homework.poke.data.remote.dto


import com.google.gson.annotations.SerializedName

data class GenerationI(
    @SerializedName("red-blue")
    val redBlue: RedBlue,
    @SerializedName("yellow")
    val yellow: Yellow
)