package cl.eme.recipemanager.ready2eat.listing


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
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
import cl.eme.recipemanager.navigation.NavigationRoutes
import cl.eme.recipemanager.ready2eat.ReadyToEatView
import com.google.accompanist.coil.rememberCoilPainter
import org.koin.java.KoinJavaComponent

@Composable
fun ReadyToEatListing(navController: NavController) {
    val myViewModel: ReadyToEatListingViewModel by KoinJavaComponent.inject(ReadyToEatListingViewModel::class.java)

    val readyToEat = myViewModel.getReadyToEat()

    RecipeManagerTheme {
        ScrollingList(readyToEat, navController)
    }
}

@Composable
fun ScrollingList(readyToEat: List<ReadyToEatView>, navController: NavController) {

    // save the scrolling position with this state
    val scrollState = rememberLazyListState()

    Scaffold(
        topBar = { RecipesTopBar() },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {navController.navigate(NavigationRoutes.readyToEatAdd)},
                backgroundColor = Color(0xFFFFA726),
                contentColor = Color.White
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Añadir"
                )
            }
        },
        isFloatingActionButtonDocked = true,
        floatingActionButtonPosition = FabPosition.End
    ) {
        LazyColumn(
            state = scrollState,
            verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_normal))
        ) {
            items(items = readyToEat, itemContent = {
                ListItem(readyToEat = it)
            })
        }
    }
}


@Composable
fun ListItem(readyToEat: ReadyToEatView) {
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
            RecipeTitleContent(readyToEat.name)

            Divider(
                color = Color.Gray,
                modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_normal))
            )
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

@Preview(showBackground = true, name = "listing recipes")
@Composable
fun ListingPreview() {
    val recipes = listOf(
        ReadyToEatView(
            name = "algo",
            freezeDate = "fecha",
            maxDurationInDays = "90",
            locationDescription = "cajon 2"
        ),
        ReadyToEatView(
            name = "algo2",
            freezeDate = "fecha 2",
            maxDurationInDays = "10",
            locationDescription = "cajon 1"
        )
    )

    RecipeManagerTheme {
        ScrollingList(recipes, rememberNavController())
    }
}

