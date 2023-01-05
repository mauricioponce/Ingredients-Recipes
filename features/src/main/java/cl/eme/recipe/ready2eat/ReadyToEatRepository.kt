package cl.eme.recipe.ready2eat


import cl.eme.recipe.core.domain.dto.ReadyToEat
import cl.eme.recipe.core.failure.Failure
import cl.eme.recipe.core.domain.Result

interface ReadyToEatRepository {
    fun getReadyToEat(): Result<Failure, List<ReadyToEat>>

    fun createReadyToEat(readyToEat: ReadyToEat): Result<Failure, ReadyToEat>
}