package cl.eme.recipe.listing.presentation

import androidx.lifecycle.ViewModel
import cl.eme.recipe.core.domain.BResult
import cl.eme.recipe.core.domain.dto.Recipe
import cl.eme.recipe.listing.domain.GetRecipesUseCase

class ListingViewModel (private val getRecipesUseCase: GetRecipesUseCase) : ViewModel() {

    fun getRecipes() : List<Recipe> {
        return when (val result = getRecipesUseCase()) {
            is BResult.Success -> (result.value)
            is BResult.Failure -> (emptyList())
        }
    }
}