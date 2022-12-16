package cl.eme.recipemanager.ready2eat.add


import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.material.Text
import androidx.compose.ui.res.stringResource
import cl.eme.recipemanager.R

@Composable
fun AddReadyToEatScreen() {
    //val myViewModel: ReadyToEatListingViewModel by KoinJavaComponent.inject(ReadyToEatListingViewModel::class.java)

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        Text(stringResource(id = R.string.add_ready_to_eat))
    }
}

@Preview
@Composable
fun AddReadyToEatScreenPreview() {
    AddReadyToEatScreen()
}