package cl.eme.recipe.listing.domain

import cl.eme.recipe.core.domain.RecipesRepository
import cl.eme.recipe.core.domain.SimpleResult
import cl.eme.recipe.core.domain.dto.Ingredient

interface GetIngredientsUseCase {
    operator fun invoke(): SimpleResult<List<Ingredient>>
}

class GetIngredientsUseCaseImp(private val recipeRepository: RecipesRepository) :
    GetIngredientsUseCase {
    override operator fun invoke(): SimpleResult<List<Ingredient>> =
        recipeRepository.getIngredients()
}