package fm.homework.poke.presentation

object AppDestinations {
    const val SELECTOR_ROUTE = "selector_screen"
    const val DETAILS_ROUTE = "details_screen" // Base route for details
    const val DETAILS_NAME_ARG = "name" // Argument key
    val detailsRouteWithArg = "$DETAILS_ROUTE/{$DETAILS_NAME_ARG}" // Full route pattern
}