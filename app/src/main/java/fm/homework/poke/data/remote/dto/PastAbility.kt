package fm.homework.poke.data.remote.dto


import com.google.gson.annotations.SerializedName

data class PastAbility(
    @SerializedName("abilities")
    val abilities: List<AbilityXX>,
    @SerializedName("generation")
    val generation: Generation
)