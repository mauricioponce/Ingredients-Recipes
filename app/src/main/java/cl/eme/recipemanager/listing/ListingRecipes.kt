package cl.eme.recipemanager.listing

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import cl.eme.recipemanager.R
import cl.eme.recipemanager.RecipesTopBar
import cl.eme.recipemanager.ui.theme.RecipeManagerTheme
import cl.eme.recipemanager.ui.theme.Shapes
import cl.eme.recipe.core.domain.dto.Ingredient
import cl.eme.recipe.core.domain.dto.Recipe
import cl.eme.recipe.listing.presentation.ListingViewModel
import com.google.accompanist.coil.rememberCoilPainter
import org.koin.java.KoinJavaComponent

@Composable
fun Listing(navController: NavController) {
    val myViewModel: ListingViewModel by KoinJavaComponent.inject(ListingViewModel::class.java)

    val recipes = myViewModel.getRecipes()

    RecipeManagerTheme {
        ScrollingList(recipes, navController)
    }
}

@Composable
fun ScrollingList(recipes: List<Recipe>, navController: NavController) {

    // save the scrolling position with this state
    val scrollState = rememberLazyListState()

    Scaffold(
        topBar = { RecipesTopBar() },
        isFloatingActionButtonDocked = true,
        floatingActionButtonPosition = FabPosition.End
        ) {
        LazyColumn(
            state = scrollState,
            verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_normal))
        ) {
            items(items = recipes, itemContent = {
                ListItem(recipe = it)
            })
        }
    }
}


@Composable
fun ListItem(recipe: Recipe) {
    Card(
        shape = Shapes.medium,
        elevation = dimensionResource(id = R.dimen.padding_normal),
        modifier = Modifier
            .fillMaxWidth()
            .padding(dimensionResource(id = R.dimen.padding_normal))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    start = dimensionResource(id = R.dimen.padding_big),
                    end = dimensionResource(id = R.dimen.padding_big)
                )
        ) {
            RecipeTitleContent(recipe.name)

            RecipeGraphicsContent(recipe.imgUrl)

            Divider(
                color = Color.Gray,
                modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_normal))
            )

            RecipeIngredientsContent(recipe.ingredients)

            RecipeCookingTimeContent(recipe.prepTime.toString())
        }
    }
}

@Composable
fun RecipeTitleContent(name: String) =
    Text(
        text = name,
        modifier = Modifier
            .padding(
                top = dimensionResource(id = R.dimen.padding_big),
                bottom = dimensionResource(id = R.dimen.padding_normal)
            )
            .fillMaxWidth(),
        style = MaterialTheme.typography.h6,
        textAlign = TextAlign.Center
    )


@Composable
fun RecipeGraphicsContent(imgUrl: String) =
    Row(
        modifier = Modifier
            .fillMaxWidth(), horizontalArrangement = Arrangement.Center
    ) {
        Image(
            painter = rememberCoilPainter(
                request = imgUrl,
                previewPlaceholder = R.drawable.zapallo_relleno,
                fadeIn = true
            ),
            contentDescription = stringResource(id = R.string.recipe_image_content_description),
            modifier = Modifier
                .height(200.dp)
                .width(200.dp)
        )
    }


@Composable
fun RecipeIngredientsContent(ingredients: List<Ingredient>) {
    ingredients.forEach {
        Row(modifier = Modifier.padding(bottom = dimensionResource(id = R.dimen.padding_normal))) {
            Icon(
                painter = painterResource(id = R.drawable.ic_baseline_check_box_24),
                contentDescription = "",
                modifier = Modifier.padding(end = dimensionResource(id = R.dimen.padding_normal))
            )
            Text(text = it.name)
        }

    }
}

@Composable
fun RecipeCookingTimeContent(prepTime: String) {
    Row(
        modifier = Modifier.padding(
            top = dimensionResource(id = R.dimen.padding_normal),
            bottom = dimensionResource(id = R.dimen.padding_big)
        )
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_baseline_timelapse_24),
            contentDescription = ""
        )
        Text(text = prepTime)
    }
}


@Preview(showBackground = true, name = "listing recipes")
@Composable
fun ListingPreview() {
    val recipes = listOf(
        Recipe(
            1,
            "Zapallo italiano relleno",
            listOf(
                Ingredient(id = 1, name = "Zapallo italiano"),
                Ingredient(id = 2, name = "carne molida"),
                Ingredient(id = 3, name = "cebolla")
            ),
            "Carne molida, proteina de soya",
            90
        ),
        Recipe(
            2,
            "Guiso de Zapallo italiano",
            listOf(
                Ingredient(id = 1, name = "Zapallo italiano"),
                Ingredient(id = 2, name = "carne molida"),
                Ingredient(id = 3, name = "cebolla")
            ),
            "Carne molida, proteina de soya",
            60
        )
    )

    RecipeManagerTheme {
        ScrollingList(recipes, rememberNavController())
    }
}