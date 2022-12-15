package cl.eme.recipe.core.domain.dto

import java.util.Date

data class ReadyToEat(
    val name: String,
    val freezeDate: Date,
    val maxDurationInDays: Int,
    val locationDescription: String
)