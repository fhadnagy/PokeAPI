package fm.homework.poke.presentation.details

import androidx.activity.result.launch
import androidx.compose.animation.core.copy
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fm.homework.poke.common.Resource
import fm.homework.poke.domain.use_case.GetPokemonUseCase
import fm.homework.poke.presentation.AppDestinations
import fm.homework.poke.presentation.selector.SelectorState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.fold
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
// Assuming SelectorScreen takes navController to navigate
class DetailsViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getPokemonUseCase: GetPokemonUseCase,
): ViewModel(){

}