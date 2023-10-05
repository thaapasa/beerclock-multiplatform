package fi.tuska.beerclock.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import fi.tuska.beerclock.domain.Gender

@Composable
fun MainScreen() {

    var ageText by remember { mutableStateOf("70") }
    var gender by remember { mutableStateOf(Gender.MALE) }

    Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        TextField(value = ageText,
            onValueChange = { ageText = it },
            label = { Text(text = strings.settings.ageLabel) })
        GenderSelector(initialValue = gender)
    }
}