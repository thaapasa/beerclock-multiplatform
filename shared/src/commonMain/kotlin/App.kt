package fi.tuska.beerclock.common

import androidx.compose.runtime.Composable
import fi.tuska.beerclock.common.ui.MainLayout
import fi.tuska.beerclock.common.ui.MainScreen

@Composable
fun App() {
    MainLayout(content = { MainScreen() })
}

expect fun getPlatformName(): String
