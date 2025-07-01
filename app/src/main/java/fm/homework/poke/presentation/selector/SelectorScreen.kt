package fm.homework.poke.presentation.selector

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items // Import for LazyColumn items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator // For loading state
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField // For the dropdown
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

// Assuming your ViewModel exposes a StateFlow like this:
// data class SelectorUiState(
//     val isLoading: Boolean = false,
//     val error: String? = null,
//     val itemList: List<String> = emptyList(), // Your actual item type
//     val selectedItem: String? = null // Your actual selected item type
// )

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectorScreen(
    navigateToDetails: (String) -> Unit,
    viewModel: SelectorViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState() // Assuming uiState is a StateFlow in your ViewModel

    var expanded by remember { mutableStateOf(false) }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {
        // Dropdown Menu
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded },
            modifier = Modifier.fillMaxWidth()
        ) {
            TextField(
                value = uiState.selectedType ?: "Select an Type", // Display selected item or placeholder
                onValueChange = {}, // Not directly editable
                readOnly = true,
                label = { Text("Item Selection") },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                },
                colors = ExposedDropdownMenuDefaults.textFieldColors(),
                modifier = Modifier.menuAnchor() // Important for positioning the dropdown
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                DropdownMenuItem(
                    text = { Text("All types") }, // Display the item
                    onClick = {
                        viewModel.onItemSelected("All types") // Call ViewModel function on selection
                        expanded = false
                    }
                )
                uiState.availableTypes.forEach { selectionOption -> // Replace with your actual list from uiState
                    DropdownMenuItem(
                        text = { Text(selectionOption) }, // Display the item
                        onClick = {
                            viewModel.onItemSelected(selectionOption) // Call ViewModel function on selection
                            expanded = false
                        }
                    )
                }
            }
        }

        // Content Box for Loading, Error, or Success (LazyColumn)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f) // Takes remaining space
                .padding(top = 16.dp),
            contentAlignment = Alignment.Center
        ) {
            when {
                uiState.isLoadingList -> {
                    CircularProgressIndicator()
                }
                uiState.error != null -> {
                    Text("Error: ${uiState.error}")
                }
                else -> {
                    // Success state: Show LazyColumn with data based on selectedItem
                    // You might want to filter or fetch data for the LazyColumn
                    // based on uiState.selectedItem
                    LazyColumn(modifier = Modifier.fillMaxSize()) {
                        // Example: Displaying items related to the selection
                        // This is a placeholder. Adapt it to your actual data structure.
                        items(uiState.filteredItems) { itemData -> // Replace with your actual data list
                            PokeListItem(pokemon = itemData, onSelected = {name-> navigateToDetails(name)}, onCatchToggle = { name -> viewModel.onCatchToggle(name) })
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun PokeListItem(pokemon: RowPokemonData, onSelected: (String) -> Unit, onCatchToggle: (String) -> Unit) {
    val borderColor = if (pokemon.caught) Color.Yellow else Color(0xFFADD8E6) // Light Blue

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        // Pokemon Name
        Surface(
            modifier = Modifier.widthIn(min =200.dp).clickable{onSelected(pokemon.name)},
            shape = RoundedCornerShape(8.dp),
            border = BorderStroke(2.dp, borderColor)
        )  {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(8.dp)
            ){
                Text(
                    text = pokemon.name,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(8.dp)
                )
                Text(
                    text = pokemon.type,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(8.dp)
                )
                Text(
                    text = if (pokemon.caught) "Caught" else "-",
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }


        // Action Button
        Button(
            onClick = { onCatchToggle(pokemon.name)},
            colors = ButtonDefaults.buttonColors(containerColor = borderColor),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.width(100.dp)
        ) {
            Text(
                text = if (pokemon.caught) "Release" else "Catch",
                color = if (pokemon.caught) Color.Black else Color.White
            )
        }
    }
}