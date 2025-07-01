package fm.homework.poke.domain.model

data class PokeItemData(
    val name: String,
    val types: List<String>,
    val caught: Boolean
)
