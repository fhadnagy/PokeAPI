package fm.homework.poke.data.remote.dto


import com.google.gson.annotations.SerializedName

data class GenerationViX(
    @SerializedName("omega-ruby-alpha-sapphire")
    val omegaRubyAlphaSapphire: OmegaRubyAlphaSapphireX,
    @SerializedName("x-y")
    val xY: XYX
)