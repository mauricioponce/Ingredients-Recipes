package cl.eme.recipe.applayout.readytoeat.listing

import cl.eme.recipe.core.domain.dto.ReadyToEat
import java.text.SimpleDateFormat
import java.util.Date

data class ReadyToEatView(
    val name: String,
    val freezeDate: String,
    val maxDurationInDays: String,
    val locationDescription: String,
    val quantity: Int
)



fun ReadyToEat.toItemView() = ReadyToEatView(
    this.name,
    this.freezeDate.toPrettyFormat(),
    this.maxDurationInDays.toString(),
    this.locationDescription,
    this.quantity
)

fun ReadyToEatView.toDomain() = ReadyToEat(
    this.name,
    Date(),
    this.maxDurationInDays.toInt(),
    this.locationDescription,
    this.quantity
)


val pattern = SimpleDateFormat("dd-MM-yy")
private fun Date.toPrettyFormat(): String {
    return pattern.format(this)
}
