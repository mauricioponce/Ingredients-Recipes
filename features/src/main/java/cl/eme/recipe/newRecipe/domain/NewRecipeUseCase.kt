package cl.eme.recipe.newrecipe.businesslogic

import cl.eme.recipe.core.domain.RecipesRepository
import cl.eme.recipe.core.domain.SimpleResult
import cl.eme.recipe.core.domain.dto.Recipe

interface NewRecipeUseCase {
    operator fun invoke(recipe: Recipe): SimpleResult<Recipe>
}

class NewRecipeUseCaseImp(private val recipeRepository: RecipesRepository) :
    NewRecipeUseCase {

    override operator fun invoke(recipe: Recipe): SimpleResult<Recipe> = recipeRepository.createRecipe(recipe)

}