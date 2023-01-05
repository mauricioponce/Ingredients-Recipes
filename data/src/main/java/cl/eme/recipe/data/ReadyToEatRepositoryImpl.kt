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
            freezeDate = Date(1672102673),
            maxDurationInDays = 50,
            locationDescription = "cajón 3",
            1
        ),
        ReadyToEat(
            name = "pantrucas",
            freezeDate = Date(1672102673),
            maxDurationInDays = 90,
            locationDescription = "cajón 3",
            1
        ),
        ReadyToEat(
            name = "Quinoa con verduras",
            freezeDate = Date(1672102673),
            maxDurationInDays = 20,
            locationDescription = "cajón 1",
            1
        )
    )

    override fun getReadyToEat(): Result<Failure, List<ReadyToEat>> {
        return Result.Right(readyToEat)
    }

    override fun createReadyToEat(newReadyToEat: ReadyToEat): Result<Failure, ReadyToEat> {
        this.readyToEat.add(newReadyToEat)
        return Result.Right(newReadyToEat)
    }
}