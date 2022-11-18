package cl.eme.recipe.newrecipe.presentation

import androidx.lifecycle.ViewModel
import cl.eme.recipe.core.domain.dto.Ingredient
import cl.eme.recipe.core.domain.dto.Recipe
import cl.eme.recipe.newrecipe.businesslogic.NewRecipeUseCase

data class RecipeView(val name: String, val ingredient: List<Ingredient>, val protein: String, val prepTime: Int)


class NewRecipeViewModel(private val newRecipeUseCase: NewRecipeUseCase) : ViewModel() {

    fun createRecipe(recipeView: RecipeView) {
        val recipe = Recipe(0, recipeView.name, recipeView.ingredient, recipeView.protein, recipeView.prepTime, "")
    }
}