package fm.homework.poke.presentation.selector

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fm.homework.poke.common.Resource
import fm.homework.poke.domain.use_case.GetAllPokemonRowsUseCase
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SelectorViewModel @Inject constructor(
    private val getAllPokemonRowsUseCase: GetAllPokemonRowsUseCase
): ViewModel(){
    private  val _state = mutableStateOf((SelectorState()))
    val state: State<SelectorState> = _state

    private fun getAllPokemonRows(){
        getAllPokemonRowsUseCase().onEach { result ->
            when(result){
                is Resource.Success -> {
                    _state.value = SelectorState(filteredItems = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value = SelectorState(error = result.message ?: "An unexpected error occured")
                }
                is Resource.Loading -> {
                    _state.value = SelectorState(isLoadingList = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    init {
        getAllPokemonRows()
    }


}