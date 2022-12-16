package cl.eme.recipemanager.ready2eat

import cl.eme.recipe.core.domain.dto.ReadyToEat

data class ReadyToEatView(
    val name: String,
    val freezeDate: String,
    val maxDurationInDays: String,
    val locationDescription: String
)

fun ReadyToEat.toView(): ReadyToEatView = ReadyToEatView(
    name = this.name,
    freezeDate = this.freezeDate.toString(),
    maxDurationInDays = this.maxDurationInDays.toString(),
    locationDescription = this.locationDescription
)