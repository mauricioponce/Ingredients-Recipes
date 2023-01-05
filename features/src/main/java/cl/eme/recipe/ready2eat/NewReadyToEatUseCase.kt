package cl.eme.recipe.ready2eat

import cl.eme.recipe.core.domain.Result
import cl.eme.recipe.core.domain.dto.ReadyToEat
import cl.eme.recipe.core.failure.Failure

interface NewReadyToEatUseCase {
    operator fun invoke(recipe: ReadyToEat): Result<Failure, ReadyToEat>
}

class NewReadyToEatUseCaseImp(private val recipeRepository: ReadyToEatRepository) :
    NewReadyToEatUseCase {

    override operator fun invoke(recipe: ReadyToEat): Result<Failure, ReadyToEat> =
        recipeRepository.createReadyToEat(recipe)
}