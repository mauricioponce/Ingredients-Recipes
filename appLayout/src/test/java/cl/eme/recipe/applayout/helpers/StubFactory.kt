package cl.eme.recipe.applayout.helpers

import cl.eme.recipe.core.domain.dto.Ingredient
import cl.eme.recipe.core.domain.dto.Recipe

class StubFactory {
    private val ingredients: MutableList<Ingredient> = mutableListOf(
        Ingredient(id = 1, name = "Zapallo italiano"),
        Ingredient(id = 2, name = "carne molida"),
        Ingredient(id = 3, name = "cebolla"),
        Ingredient(id = 4, name = "Lentejas"),
        Ingredient(id = 5, name = "zanahoria"),
        Ingredient(id = 6, name = "Atún"),
        Ingredient(id = 7, name = "huevo"),
        Ingredient(id = 8, name = "Posta negra"),
        Ingredient(id = 9, name = "tomate"),
    )

    val recipe = Recipe(
        1,
        "Zapallo italiano relleno",
        listOf(
            ingredients[0],
            ingredients[1],
            ingredients[2],
        ),
        "Carne molida, proteina de soya",
        90,
        "https://www.chicureohoy.cl/wp-content/uploads/2018/08/zapallo.png"
    )

    val recipes: List<Recipe> =
        listOf(
            recipe
        )
}