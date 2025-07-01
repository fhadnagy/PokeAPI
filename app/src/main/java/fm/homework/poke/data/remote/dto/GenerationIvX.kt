package fm.homework.poke.data.remote.dto


import com.google.gson.annotations.SerializedName

data class GenerationIvX(
    @SerializedName("diamond-pearl")
    val diamondPearl: DiamondPearlX,
    @SerializedName("heartgold-soulsilver")
    val heartgoldSoulsilver: HeartgoldSoulsilverX,
    @SerializedName("platinum")
    val platinum: PlatinumX
)