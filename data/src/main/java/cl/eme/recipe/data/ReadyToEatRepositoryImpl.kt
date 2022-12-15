package cl.eme.recipe.data

import cl.eme.recipe.core.domain.Result
import cl.eme.recipe.core.domain.dto.ReadyToEat
import cl.eme.recipe.core.failure.Failure
import cl.eme.recipe.ready2eat.ReadyToEatRepository
import java.util.*

class ReadyToEatRepositoryImpl: ReadyToEatRepository {

    private val readyToEat: MutableList<ReadyToEat> = mutableListOf(
        ReadyToEat(
            name = "porotos",
            freezeDate = Date(),
            maxDurationInDays = 50,
            locationDescription = "1"
        )
    )

    override fun getReadyToEat(): Result<Failure, List<ReadyToEat>> {
        return Result.Right(readyToEat)
    }
}