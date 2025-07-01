package fm.homework.poke.presentation.details

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.FilterQuality
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
import fm.homework.poke.domain.model.PokemonDetails

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
                PokemonDetailsView(pokemonDetails = state.pokemonDetails!!,{viewModel.onCaugthToggle()})
            } // Add else block if needed
        }
    }
}

@Composable
fun PokemonDetailsView(pokemonDetails: PokemonDetails, toggle: () -> Unit) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val outlineColor = if (pokemonDetails.caught) Color(0xFFFFCB05) else Color(0xFF165CDF)
            // Image Card
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(pokemonDetails.imageUrl)
                    .crossfade(true)
                    .placeholder(R.drawable.downloading) // Replace with your placeholder
                    .size(coil.size.Size.ORIGINAL) // Load original size to avoid blurring
                    .build(),
                contentDescription = pokemonDetails.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
                    .border(3.dp, outlineColor),
                contentScale = ContentScale.FillWidth, // Scale to fill width without interpolation
                filterQuality = FilterQuality.None // Use nearest neighbor scaling
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Info Card
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, Color.LightGray)
            ) {
                InfoRow("Name", pokemonDetails.name)
                InfoRow("Weight", "${pokemonDetails.weight}kg", Color(0xFFFFF9C4))
                InfoRow("Height", "${pokemonDetails.height}m")
                InfoRow("Abilities", pokemonDetails.abilities.joinToString("\n"), Color(0xFFFFF9C4))
                InfoRow("Status", if (pokemonDetails.caught) "Caught" else "-", Color(0xFFEEEEEE))
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = toggle,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp,16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = outlineColor
                )
            ) {
                Text(text = if (pokemonDetails.caught) "Release" else "Catch", color = if (pokemonDetails.caught) Color.Black else Color.White)
            }
        }
}


@Composable
fun InfoRow(label: String, value: String, backgroundColor: Color = Color(0xFFE3F2FD)) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(backgroundColor)
            .padding(8.dp)
    ) {
        Text(
            text = label,
            modifier = Modifier.weight(1f),
            fontWeight = FontWeight.Bold
        )
        Text(
            text = value,
            modifier = Modifier.weight(2f),
            fontWeight = FontWeight.Medium
        )
    }
}

