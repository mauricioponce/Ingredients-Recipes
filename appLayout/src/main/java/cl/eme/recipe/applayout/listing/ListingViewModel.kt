package cl.eme.recipe.applayout.listing

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import cl.eme.recipe.applayout.platform.BaseViewModel
import cl.eme.recipe.core.domain.Result
import cl.eme.recipe.core.domain.dto.Recipe
import cl.eme.recipe.listing.domain.GetRecipesUseCase

class ListingViewModel (private val getRecipesUseCase: GetRecipesUseCase) : BaseViewModel() {

    private val _recipes: MutableLiveData<List<Recipe>> = MutableLiveData()
    val recipes: LiveData<List<Recipe>> = _recipes

    fun getRecipes() {
        when (val result = getRecipesUseCase()) {
            is Result.Right -> _recipes.value = result.value
            is Result.Left -> handleFailure(result.failure)
        }
    }
}

