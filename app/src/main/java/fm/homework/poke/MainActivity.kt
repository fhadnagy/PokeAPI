package fm.homework.poke

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dagger.hilt.android.AndroidEntryPoint
import fm.homework.poke.presentation.AppDestinations
import fm.homework.poke.presentation.details.DetailsScreen
import fm.homework.poke.presentation.selector.SelectorScreen

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // YourAppTheme { // Apply your theme
            AppNavigation()
            // }
        }
    }
}

@Composable
fun AppNavigation() {
    val navController: NavHostController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = AppDestinations.SELECTOR_ROUTE // Your starting screen
    ) {
        composable(route = AppDestinations.SELECTOR_ROUTE) {
            SelectorScreen(navigateToDetails = { pokemonName ->
                navController.navigate("${AppDestinations.DETAILS_ROUTE}/$pokemonName")
            })
        }

        composable(
            route = AppDestinations.detailsRouteWithArg,
            arguments = listOf(navArgument(AppDestinations.DETAILS_NAME_ARG) {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            val pokemonName = backStackEntry.arguments?.getString(AppDestinations.DETAILS_NAME_ARG)
            DetailsScreen()
        }
    }
}