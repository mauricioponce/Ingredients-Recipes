package cl.eme.recipe.core.domain

import cl.eme.recipe.core.domain.dto.Ingredient
import cl.eme.recipe.core.domain.dto.Recipe

interface RecipesRepository {
    fun getRecipes(): SimpleResult<List<Recipe>>

    fun createRecipe(recipe: Recipe): SimpleResult<Recipe>

    fun getIngredients(): SimpleResult<List<Ingredient>>
}
