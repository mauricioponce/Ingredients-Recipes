package cl.eme.recipe.core.domain

import cl.eme.recipe.core.domain.dto.Ingredient
import cl.eme.recipe.core.domain.dto.Recipe

interface RecipesRepository {
    fun getRecipes(): Result<List<Recipe>>

    fun createRecipe(recipe: Recipe): Result<Recipe>

    fun getIngredients(): Result<List<Ingredient>>
}
