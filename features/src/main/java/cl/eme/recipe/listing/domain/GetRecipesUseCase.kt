package cl.eme.recipe.listing.domain

import cl.eme.recipe.core.domain.RecipesRepository
import cl.eme.recipe.core.domain.SimpleResult
import cl.eme.recipe.core.domain.dto.Recipe

interface GetRecipesUseCase {
    operator fun invoke(): SimpleResult<List<Recipe>>
}

class GetRecipesUseCaseImp(private val recipeRepository: RecipesRepository) : GetRecipesUseCase {
    override operator fun invoke(): SimpleResult<List<Recipe>> = recipeRepository.getRecipes()
}