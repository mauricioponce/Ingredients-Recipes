package cl.eme.rm.listing.businesslogic

import cl.eme.rm.core.domain.RecipesRepository
import cl.eme.rm.core.domain.SimpleResult
import cl.eme.rm.core.domain.dto.Recipe

interface GetRecipesUseCase {
    operator fun invoke(): SimpleResult<List<Recipe>>
}

class GetRecipesUseCaseImp(private val recipeRepository: RecipesRepository) : GetRecipesUseCase {
    override operator fun invoke(): SimpleResult<List<Recipe>> = recipeRepository.getRecipes()
}