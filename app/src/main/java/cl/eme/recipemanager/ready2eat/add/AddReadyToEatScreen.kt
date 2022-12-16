package cl.eme.recipemanager.ready2eat.add


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
       content()
    }
}

@Composable
fun defaultSpacer() = Spacer(modifier = Modifier.height(8.dp))

@Composable
fun content() {
    defaultSpacer()

    Text(stringResource(id = R.string.add_ready_to_eat))
    defaultSpacer()

    SimpleOutlinedTextField(label = stringResource(R.string.name_ready_to_eat))
    defaultSpacer()

    SimpleOutlinedTextField(label = stringResource(R.string.freeze_date))
    defaultSpacer()

    SimpleOutlinedNumberField(label = stringResource(R.string.max_duration_in_days))
    defaultSpacer()

    SimpleOutlinedTextField(label = stringResource(R.string.location_ready_to_eat))
    defaultSpacer()

    SimpleOutlinedNumberField(label = stringResource(R.string.quantity))
    defaultSpacer()

    Button(
        onClick = { },
        contentPadding = PaddingValues(
            start = 20.dp,
            top = 12.dp,
            end = 20.dp,
            bottom = 12.dp
        )
    ) {
        Text(stringResource(R.string.save))
    }
}

@Composable
fun SimpleOutlinedTextField(label: String) {
    var text by remember { mutableStateOf("") }

    OutlinedTextField(
        value = text,
        onValueChange = { text = it },
        label = { Text(label) }
    )
}

@Composable
fun SimpleOutlinedNumberField(label: String) {
    var text by remember { mutableStateOf("") }

    OutlinedTextField(
        value = text,
        onValueChange = { text = it },
        label = { Text(label) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
    )
}

@Preview
@Composable
fun AddReadyToEatScreenPreview() {
    AddReadyToEatScreen()
}