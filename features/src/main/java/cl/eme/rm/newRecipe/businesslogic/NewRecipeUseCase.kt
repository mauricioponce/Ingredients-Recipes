package cl.eme.rm.newrecipe.businesslogic

import cl.eme.rm.core.domain.RecipesRepository
import cl.eme.rm.core.domain.SimpleResult
import cl.eme.rm.core.domain.dto.Recipe

interface NewRecipeUseCase {
    operator fun invoke(recipe: Recipe): SimpleResult<Recipe>
}

class NewRecipeUseCaseImp(private val recipeRepository: RecipesRepository) :
    NewRecipeUseCase {

    override operator fun invoke(recipe: Recipe): SimpleResult<Recipe> = recipeRepository.createRecipe(recipe)

}