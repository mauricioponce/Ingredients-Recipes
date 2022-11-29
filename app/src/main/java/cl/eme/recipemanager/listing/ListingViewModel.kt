package cl.eme.recipemanager.listing

import androidx.lifecycle.ViewModel
import cl.eme.recipe.core.domain.Result
import cl.eme.recipe.core.domain.dto.Recipe
import cl.eme.recipe.listing.domain.GetRecipesUseCase

class ListingViewModel (private val getRecipesUseCase: GetRecipesUseCase) : ViewModel() {

    fun getRecipes() : List<Recipe> {
        return when (val result = getRecipesUseCase()) {
            is Result.Right -> result.value
            is Result.Left -> emptyList()
        }
    }
}

