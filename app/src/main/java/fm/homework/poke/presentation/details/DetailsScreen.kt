package fm.homework.poke.presentation.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import fm.homework.poke.R
import fm.homework.poke.domain.model.Pokemon

@Composable
fun DetailsScreen(
    viewModel: DetailsViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        when {
            state.isLoading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
            state.error.isNotBlank() -> {
                Text(
                    text = state.error,
                    color = Color.Red,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp) // Add padding
                        .align(Alignment.Center)
                )
            }
            state.pokemonDetails != null -> {
                PokemonDetailsView(pokemonDetails = state.pokemonDetails!!)
            } // Add else block if needed
        }
    }
}

@Composable
fun PokemonDetailsView(pokemonDetails: Pokemon) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(pokemonDetails.imageUrl)
                .crossfade(true)
                .placeholder(R.drawable.downloading) // Replace with your placeholder
                .build(),
            contentDescription = pokemonDetails.name,
            modifier = Modifier
                .size(200.dp)
                .padding(bottom = 16.dp),
            contentScale = ContentScale.Fit
        )

        Text(
            text = pokemonDetails.name.replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() },
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(16.dp))
        DetailItem(label = "Name", value = pokemonDetails.name, color = Color.DarkGray)
        DetailItem(label = "Weight", value = "${pokemonDetails.weight}", color = Color.LightGray)
        DetailItem(label = "Height", value = "${pokemonDetails.height}", color = Color.DarkGray)
        DetailItem(label = "Abilities", value = pokemonDetails.abilities.joinToString("\n"), color = Color.LightGray)
        DetailItem(label = "Status", value = if (pokemonDetails.caught) "Caught" else "-", color = Color.DarkGray)
    }
}

@Composable
fun DetailItem(label: String, value: String, color: Color) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = "$label:", fontWeight = FontWeight.SemiBold, color = color, textAlign = TextAlign.Start, modifier = Modifier.weight(1f))
        Text(text = value, color = color, textAlign = TextAlign.Start, modifier = Modifier.weight(1f))
    }
}

