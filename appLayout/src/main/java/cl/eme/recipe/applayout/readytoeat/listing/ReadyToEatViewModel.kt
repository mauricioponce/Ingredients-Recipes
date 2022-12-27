package cl.eme.recipe.applayout.readytoeat.listing

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import cl.eme.recipe.applayout.platform.BaseViewModel
import cl.eme.recipe.core.domain.Result
import cl.eme.recipe.core.domain.dto.ReadyToEat
import cl.eme.recipe.ready2eat.GetReadyToEatUseCase
import java.util.*

class ReadyToEatViewModel (private val getReadyToEatUseCase: GetReadyToEatUseCase) : BaseViewModel() {

    private val _recipes: MutableLiveData<List<ReadyToEatView>> = MutableLiveData()
    val readyToEat: LiveData<List<ReadyToEatView>> = _recipes

    init {
        getRecipes()
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    internal fun getRecipes() {
        when (val result = getReadyToEatUseCase()) {
            is Result.Right -> _recipes.value = result.value.map { it.toItemView() }
            is Result.Left -> handleFailure(result.failure)
        }
    }
}
