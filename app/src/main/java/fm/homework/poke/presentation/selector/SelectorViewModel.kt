package fm.homework.poke.presentation.selector

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fm.homework.poke.common.Resource
import fm.homework.poke.domain.use_case.GetAllPokemonRowsUseCase
import fm.homework.poke.domain.use_case.GetAllPokemonsByTypeUseCase
import fm.homework.poke.domain.use_case.GetAllTypesUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class SelectorViewModel @Inject constructor(
    private val getAllPokemonRowsUseCase: GetAllPokemonRowsUseCase,
    private val getAllTypesUseCase: GetAllTypesUseCase,
    private val getAllPokemonsByTypeUseCase: GetAllPokemonsByTypeUseCase
): ViewModel(){
    private val _uiState = MutableStateFlow(SelectorState())
    val uiState: StateFlow<SelectorState> = _uiState.asStateFlow()

    private val searchJob: Job? = null
    private var resultList = listOf<RowPokemonData>()

    private fun getAllPokemonRows(){
        getAllPokemonRowsUseCase().onEach { result ->
            when(result){
                is Resource.Success -> {
                    _uiState.update { it.copy(filteredItems = result.data ?: emptyList(), isLoadingList = false) }
                    resultList = result.data ?: emptyList()
                    _uiState.update { it.copy(filteredItems = resultList.filter { item -> item.name.contains(_uiState.value.searchQuery) }) }
                }
                is Resource.Error -> {
                    _uiState.update { it.copy(error = result.message ?: "An unexpected error occured", isLoadingList = false) }
                }
                is Resource.Loading -> {
                    _uiState.update { it.copy(isLoadingList = true) }
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getAllPokemonByType(type: String){
        getAllPokemonsByTypeUseCase(type).onEach { result ->
            when(result){
                is Resource.Success -> {
                    _uiState.update { it.copy(filteredItems = result.data ?: emptyList(), isLoadingList = false) }
                    resultList = result.data ?: emptyList()
                    _uiState.update { it.copy(filteredItems = resultList.filter { item -> item.name.contains(_uiState.value.searchQuery) }) }
                }
                is Resource.Error -> {
                    _uiState.update { it.copy(error = result.message ?: "An unexpected error occured", isLoadingList = false) }
                }
                is Resource.Loading -> {
                    _uiState.update { it.copy(isLoadingList = true) }
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getAllTypes(){
        getAllTypesUseCase().onEach { result ->
            when(result){
                is Resource.Success -> {
                    _uiState.update { it.copy(availableTypes = result.data ?: emptyList(), isLoadingList = false) }
                }
                is Resource.Error -> {
                    _uiState.update { it.copy(error = result.message ?: "An unexpected error occured", isLoadingList = false) }
                }
                is Resource.Loading -> {
                    _uiState.update { it.copy(isLoadingTypes = true) }
                }
            }
        }.launchIn(viewModelScope)
    }

    init {
        _uiState.update { it.copy(isLoadingList = true) }
        getAllTypes()
        onItemSelected("All types")
    }

    fun onItemSelected(selectionOption: String) {
        _uiState.update { it.copy(selectedType = selectionOption) }
        ///TODO: Implement filtering based on selectionOption
        if (selectionOption == "All types")
        {
            getAllPokemonRows()
        }
        else
        {
            getAllPokemonByType(selectionOption)

        }
    }

    fun  onCatchToggle(name: String){
        _uiState.update { it.copy(filteredItems = it.filteredItems.map { item -> if (item.name == name) item.copy(caught = !item.caught) else item }) }
    }

    fun onQueryChanged(str : String){
        _uiState.update { it.copy(searchQuery = str) }
        _uiState.update { it.copy(filteredItems = resultList.filter { item -> item.name.contains(str) }) }
    }
}