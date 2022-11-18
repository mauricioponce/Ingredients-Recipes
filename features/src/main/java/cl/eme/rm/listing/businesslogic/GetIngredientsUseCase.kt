package cl.eme.rm.listing.businesslogic

import cl.eme.rm.core.domain.RecipesRepository
import cl.eme.rm.core.domain.SimpleResult
import cl.eme.rm.core.domain.dto.Ingredient

interface GetIngredientsUseCase {
    operator fun invoke(): SimpleResult<List<Ingredient>>
}

class GetIngredientsUseCaseImp(private val recipeRepository: RecipesRepository) :
    GetIngredientsUseCase {
    override operator fun invoke(): SimpleResult<List<Ingredient>> =
        recipeRepository.getIngredients()
}