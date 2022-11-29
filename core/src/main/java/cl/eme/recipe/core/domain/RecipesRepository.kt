package cl.eme.recipe.core.domain

import cl.eme.recipe.core.domain.dto.Ingredient
import cl.eme.recipe.core.domain.dto.Recipe
import cl.eme.recipe.core.failure.Failure

interface RecipesRepository {
    fun getRecipes(): Result<Failure, List<Recipe>>

    fun createRecipe(recipe: Recipe): Result<Failure, Recipe>

    fun getIngredients(): Result<Failure, List<Ingredient>>
}
