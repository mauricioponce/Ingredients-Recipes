package cl.eme.recipe.newRecipe.domain

import cl.eme.recipe.core.domain.RecipesRepository
import cl.eme.recipe.core.domain.Result
import cl.eme.recipe.core.domain.dto.Recipe
import cl.eme.recipe.core.failure.Failure

interface NewRecipeUseCase {
    operator fun invoke(recipe: Recipe): Result<Failure, Recipe>
}

class NewRecipeUseCaseImp(private val recipeRepository: RecipesRepository) :
    NewRecipeUseCase {

    override operator fun invoke(recipe: Recipe): Result<Failure, Recipe> =
        recipeRepository.createRecipe(recipe)
}