package cl.eme.recipe.data

import cl.eme.recipe.core.domain.Result
import cl.eme.recipe.core.domain.RecipesRepository
import cl.eme.recipe.core.domain.dto.Ingredient
import cl.eme.recipe.core.domain.dto.Recipe

class RecipesRepositoryImpl : RecipesRepository {

    private val ingredients: MutableList<Ingredient> = mutableListOf(
        Ingredient(id = 1, name = "Zapallo italiano"),
        Ingredient(id = 2, name = "carne molida"),
        Ingredient(id = 3, name = "cebolla"),
        Ingredient(id = 4, name = "Lentejas"),
        Ingredient(id = 5, name = "zanahoria"),
        Ingredient(id=6, name ="Atún"),
        Ingredient(id = 7, name = "huevo"),
        Ingredient(id=8, name ="Posta negra"),
        Ingredient(id = 9, name = "tomate"),
    )

    private val recipes: MutableList<Recipe> = mutableListOf(
        Recipe(
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
        ),
        Recipe(
            2,
            "Guiso de Zapallo italiano",
            listOf(
                ingredients[0],
                ingredients[1],
                ingredients[2],
            ),
            "Carne molida, proteina de soya",
            60,
            "https://img-global.cpcdn.com/recipes/a2eb4f918fac8049/751x532cq70/guiso-zapallo-italiano-foto-principal.jpg"
        ),
        Recipe(
            3,
            "Lentejas",
            listOf(
                ingredients[3],
                ingredients[1],
                ingredients[2],
                ingredients[4],
            ),
            "Carne molida, proteina de soya",
            60,
            "https://assets.tmecosys.com/image/upload/t_web767x639/img/recipe/ras/Assets/7425EE50-1939-460B-8389-F7C100F970F0/Derivates/B2A819CD-52C6-4E24-BA83-7C10DB3C3F71.jpg"
        ),
        Recipe(
            4,
            "Tortilla de atún",
            listOf(
                ingredients[5],
                ingredients[2],
                ingredients[6],
            ),
            "Carne",
            60,
            "https://i.ytimg.com/vi/Vf9RjtdEhzw/maxresdefault.jpg"
        ),
        Recipe(
            5,
            "Carne a la olla",
            listOf(
                ingredients[7],
                ingredients[2],
                ingredients[4],
                ingredients[8],
            ),
            "Carne",
            60
        )
    )

    override fun getRecipes(): Result<List<Recipe>> = Result.Success(recipes)

    override fun createRecipe(recipe: Recipe): Result<Recipe> {
        recipes.add(recipe)
        return Result.Success(recipe)
    }

    override fun getIngredients(): Result<List<Ingredient>> = Result.Success(ingredients)
}
