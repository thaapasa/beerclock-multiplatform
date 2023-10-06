package fi.tuska.beerclock.common

import androidx.compose.runtime.Composable
import fi.tuska.beerclock.ui.MainLayout
import fi.tuska.beerclock.ui.MainScreen

@Composable
fun App() {
    MainLayout(content = { MainScreen() })
}

expect fun getPlatformName(): String
