package cl.eme.recipe.ready2eat

import cl.eme.recipe.core.domain.Result
import cl.eme.recipe.core.domain.dto.ReadyToEat
import cl.eme.recipe.core.failure.Failure

interface GetReadyToEatUseCase {
    operator fun invoke(): Result<Failure, List<ReadyToEat>>
}

class GetReadyToEatUseCaseImp(private val readyToEatRepository: ReadyToEatRepository): GetReadyToEatUseCase {
    override fun invoke(): Result<Failure, List<ReadyToEat>> = readyToEatRepository.getReadyToEat()

}
