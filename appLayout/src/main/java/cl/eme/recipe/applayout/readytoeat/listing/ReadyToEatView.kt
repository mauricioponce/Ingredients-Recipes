package cl.eme.recipe.applayout.readytoeat.listing

import cl.eme.recipe.core.domain.dto.ReadyToEat
import java.util.Date

data class ReadyToEatView(
    val name: String,
    val freezeDate: Date,
    val maxDurationInDays: Int,
    val locationDescription: String,
    val quantity: Int
)


fun ReadyToEat.toItemView() = ReadyToEatView(
    this.name,
    this.freezeDate,
    this.maxDurationInDays,
    this.locationDescription,
    this.quantity
)
