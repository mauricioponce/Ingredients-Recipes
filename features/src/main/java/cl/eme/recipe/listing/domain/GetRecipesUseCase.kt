package cl.eme.recipe.listing.domain

import cl.eme.recipe.core.domain.RecipesRepository
import cl.eme.recipe.core.domain.dto.Recipe
import cl.eme.recipe.core.domain.Result

interface GetRecipesUseCase {
    operator fun invoke(): Result<List<Recipe>>
}

class GetRecipesUseCaseImp(private val recipeRepository: RecipesRepository) : GetRecipesUseCase {
    override operator fun invoke(): Result<List<Recipe>> = recipeRepository.getRecipes()
}