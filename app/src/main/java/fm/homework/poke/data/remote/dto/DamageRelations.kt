package fm.homework.poke.data.remote.dto


import com.google.gson.annotations.SerializedName

data class DamageRelations(
    @SerializedName("double_damage_from")
    val doubleDamageFrom: List<DoubleDamageFrom>,
    @SerializedName("double_damage_to")
    val doubleDamageTo: List<DoubleDamageTo>,
    @SerializedName("half_damage_from")
    val halfDamageFrom: List<HalfDamageFrom>,
    @SerializedName("half_damage_to")
    val halfDamageTo: List<HalfDamageTo>,
    @SerializedName("no_damage_from")
    val noDamageFrom: List<Any?>,
    @SerializedName("no_damage_to")
    val noDamageTo: List<Any?>
)