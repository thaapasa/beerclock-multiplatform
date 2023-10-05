package fi.tuska.beerclock

import androidx.compose.runtime.Composable
import fi.tuska.beerclock.ui.MainLayout
import fi.tuska.beerclock.ui.MainScreen

@Composable
fun App() {
    MainLayout(content = { paddingValues -> MainScreen(paddingValues) })
}

expect fun getPlatformName(): String
