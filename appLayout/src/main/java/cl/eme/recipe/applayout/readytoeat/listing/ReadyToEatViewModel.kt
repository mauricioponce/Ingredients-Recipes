package cl.eme.recipe.applayout.readytoeat.listing

import android.util.Log
import androidx.annotation.RequiresPermission
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import cl.eme.recipe.applayout.platform.BaseViewModel
import cl.eme.recipe.core.domain.Result
import cl.eme.recipe.ready2eat.GetReadyToEatUseCase
import cl.eme.recipe.ready2eat.NewReadyToEatUseCase

class ReadyToEatViewModel(
    private val getReadyToEatUseCase: GetReadyToEatUseCase,
    private val newReadyToEatUseCase: NewReadyToEatUseCase
) : BaseViewModel() {

    private val _recipes: MutableLiveData<List<ReadyToEatView>> = MutableLiveData()
    val readyToEat: LiveData<List<ReadyToEatView>> = _recipes

    private val _newRecipe: MutableLiveData<ReadyToEatView> = MutableLiveData()
    val newRecipe: LiveData<ReadyToEatView> = _newRecipe

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

    fun newReadyToEat(newReadyToEat: ReadyToEatView) {
        val a = newReadyToEat.toDomain()
        when(val result = newReadyToEatUseCase(a)) {
            is Result.Right -> _newRecipe.value = result.value.toItemView()
            is Result.Left -> handleFailure(result.failure)
        }
    }
}
