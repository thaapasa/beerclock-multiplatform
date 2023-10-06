package fi.tuska.beerclock.common.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import fi.tuska.beerclock.common.DrinksScreen
import fi.tuska.beerclock.common.SettingsScreen
import fi.tuska.beerclock.common.StatisticsScreen

@Composable
fun DrawerContent(selectScreen: (select: Screen) -> Unit) {
    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Top) {
        DropdownMenuItem(
            onClick = { selectScreen(SettingsScreen) },
            content = { Text(strings.menu.settings) }
        )
        DropdownMenuItem(
            onClick = { selectScreen(DrinksScreen) },
            content = { Text(strings.menu.drinks) }
        )
        DropdownMenuItem(
            onClick = { selectScreen(StatisticsScreen) },
            content = { Text(strings.menu.statistics) }
        )
    }
}