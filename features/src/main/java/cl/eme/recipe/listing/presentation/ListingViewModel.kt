package cl.eme.recipe.listing.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cl.eme.recipe.core.domain.Result
import cl.eme.recipe.core.domain.dto.Recipe
import cl.eme.recipe.listing.domain.GetRecipesUseCase

open class ErrorHandlerViewModel : ViewModel(){
    private val _err: MutableLiveData<Result.Err> = MutableLiveData()
    val err: LiveData<Result.Err> = _err

    fun handleError(error: Result.Err) {
        _err.value = error
    }
}

class ListingViewModel (private val getRecipesUseCase: GetRecipesUseCase) : ErrorHandlerViewModel() {

    private val _recipes: MutableLiveData<List<Recipe>> = MutableLiveData()
    val recipes: LiveData<List<Recipe>> = _recipes

    fun getRecipes() {
        when (val result = getRecipesUseCase()) {
            is Result.Ok -> handleRecipes(result.data)
            is Result.Err -> handleError(result)
        }
    }

    private fun handleRecipes(recipesList: List<Recipe>) {
        _recipes.value = recipesList
    }
}