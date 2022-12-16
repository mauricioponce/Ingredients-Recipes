package cl.eme.recipemanager.navigation

import androidx.navigation.NavController


fun NavController.navigateToAddRecipe() {
    navigate(NavigationRoutes.newRecipe)
}

class NavigationRoutes {
    companion object {
        const val listing = "listing"
        const val newRecipe = "addRecipe"
        const val readyToEatListing = "readyToEatListing"
        const val readyToEatAdd = "readyToEatAdd"
    }
}