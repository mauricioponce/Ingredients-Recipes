package cl.eme.recipe.applayout.listing

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import cl.eme.recipe.applayout.platform.BaseViewModel
import cl.eme.recipe.core.domain.Result
import cl.eme.recipe.listing.domain.GetRecipesUseCase

class ListingViewModel (private val getRecipesUseCase: GetRecipesUseCase) : BaseViewModel() {

    private val _recipes: MutableLiveData<List<RecipeItemView>> = MutableLiveData()
    val recipes: LiveData<List<RecipeItemView>> = _recipes

    init {
        getRecipes()
    }

    fun getRecipes() {
        when (val result = getRecipesUseCase()) {
            is Result.Right -> _recipes.value = result.value.map { it.toItemView() }
            is Result.Left -> handleFailure(result.failure)
        }
    }
}
