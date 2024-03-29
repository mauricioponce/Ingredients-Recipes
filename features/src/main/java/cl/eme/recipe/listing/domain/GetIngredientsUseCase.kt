package cl.eme.recipe.listing.domain

import cl.eme.recipe.core.domain.RecipesRepository
import cl.eme.recipe.core.domain.dto.Ingredient
import cl.eme.recipe.core.failure.Failure
import cl.eme.recipe.core.domain.Result

interface GetIngredientsUseCase {
    operator fun invoke(): Result<Failure, List<Ingredient>>
}

class GetIngredientsUseCaseImp(private val recipeRepository: RecipesRepository) :
    GetIngredientsUseCase {
    override operator fun invoke(): Result<Failure, List<Ingredient>> =
        recipeRepository.getIngredients()
}