package fm.homework.poke.data.remote.dto


import com.google.gson.annotations.SerializedName

data class PastDamageRelation(
    @SerializedName("damage_relations")
    val damageRelations: DamageRelations,
    @SerializedName("generation")
    val generation: GenerationXX
)