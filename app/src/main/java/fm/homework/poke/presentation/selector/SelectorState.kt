package fm.homework.poke.presentation.selector


data class SelectorState( // Consider renaming if its scope expands beyond just selection
    val isLoadingList: Boolean = false, // Was 'isLoading'
    val isLoadingTypes: Boolean = false, // New: if types are fetched

    val searchQuery: String = "", // New: for search bar

    val availableTypes: List<String> = emptyList(), // New: for dropdown population
    val selectedType: String = "", // Was 'typeSelected'

    // 'pokemonRows' can be renamed to 'filteredItems' or similar for clarity
    val filteredItems: List<RowPokemonData> = emptyList(), // List of items after filtering

    val error: String? = null // Changed to nullable for better error handling
)
