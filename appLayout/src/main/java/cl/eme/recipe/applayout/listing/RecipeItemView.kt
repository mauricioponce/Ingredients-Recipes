package cl.eme.recipe.applayout.listing

import cl.eme.recipe.core.domain.dto.Ingredient
import cl.eme.recipe.core.domain.dto.Recipe

data class RecipeItemView(
    val id: Int,
    val name: String,
    val ingredients: List<Ingredient>,
    val protein: String,
    val prepTime: String,
    val imgUrl: String = ""
)

fun Recipe.toItemView(): RecipeItemView = RecipeItemView(
    this.id,
    this.name,
    this.ingredients,
    this.protein,
    getFormatPrepTime(this.prepTime),
    this.imgUrl
)

fun getFormatPrepTime(prepTime: Int): String = "$prepTime"
