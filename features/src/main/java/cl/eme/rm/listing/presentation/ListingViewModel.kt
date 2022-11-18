package cl.eme.rm.listing.presentation

import androidx.lifecycle.ViewModel
import cl.eme.rm.core.domain.BResult
import cl.eme.rm.core.domain.dto.Recipe
import cl.eme.rm.listing.businesslogic.GetRecipesUseCase

class ListingViewModel (private val getRecipesUseCase: GetRecipesUseCase) : ViewModel() {

    fun getRecipes() : List<Recipe> {
        return when (val result = getRecipesUseCase()) {
            is BResult.Success -> (result.value)
            is BResult.Failure -> (emptyList())
        }
    }
}