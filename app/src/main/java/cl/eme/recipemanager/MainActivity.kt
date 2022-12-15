package cl.eme.recipemanager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import cl.eme.recipemanager.listing.Listing
import cl.eme.recipemanager.navigation.NavigationRoutes
import cl.eme.recipemanager.ready2eat.listing.ReadyToEatListing
import timber.log.Timber


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp()
        }

        initLog()
    }

    private fun initLog() {
        Timber.plant(Timber.DebugTree())
    }
}

@Composable
private fun MyApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = NavigationRoutes.readyToEatListing) {
        composable(NavigationRoutes.readyToEatListing) { ReadyToEatListing(navController) }
    }
}

@Composable
fun RecipesTopBar() {
    TopAppBar(title = { Text(text = stringResource(id = R.string.app_name)) })
}
