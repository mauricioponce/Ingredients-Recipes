package cl.eme.recipe.core.domain.dto

data class Recipe(
    val id: Int,
    val name: String,
    val ingredients: List<Ingredient>,
    val protein: String,
    val prepTime: Int,
    val imgUrl: String = ""
)
