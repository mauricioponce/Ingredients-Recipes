package cl.eme.recipemanager.ready2eat.listing

import androidx.lifecycle.ViewModel
import cl.eme.recipe.core.domain.Result
import cl.eme.recipe.ready2eat.GetReadyToEatUseCase
import cl.eme.recipemanager.ready2eat.ReadyToEatView
import cl.eme.recipemanager.ready2eat.toView

class ReadyToEatListingViewModel (private val getReadyToEatUseCase: GetReadyToEatUseCase): ViewModel() {
    fun getReadyToEat(): List<ReadyToEatView> {
        return when (val result = getReadyToEatUseCase()) {
            is Result.Right -> result.value.map { it.toView() }
            is Result.Left -> emptyList()
        }
    }

}