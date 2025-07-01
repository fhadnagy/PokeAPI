package fm.homework.poke.data.remote.dto


import com.google.gson.annotations.SerializedName

data class GenerationIx(
    @SerializedName("scarlet-violet")
    val scarletViolet: ScarletViolet
)