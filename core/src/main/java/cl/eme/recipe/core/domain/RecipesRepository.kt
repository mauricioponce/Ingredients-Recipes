package cl.eme.recipe.core.domain

import cl.eme.recipe.core.domain.dto.Ingredient
import cl.eme.recipe.core.domain.dto.Recipe
import cl.eme.recipe.core.domain.Result

interface RecipesRepository {
    fun getRecipes(): Result<List<Recipe>>

    fun createRecipe(recipe: Recipe): Result<Recipe>

    fun getIngredients(): Result<List<Ingredient>>
}
