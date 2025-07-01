package fm.homework.poke.domain.model

data class Pokemon(
    val name: String,
    val abilities: List<String>,
    val types: List<String>,
    val imageUrl: String?,
    val height: Int,
    val weight: Int,
    val caught: Boolean
)
