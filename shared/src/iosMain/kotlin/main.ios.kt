package fi.tuska.beerclock

import androidx.compose.ui.window.ComposeUIViewController

actual fun getPlatformName(): String = "iOS"

@Suppress("unused")
fun MainViewController() = ComposeUIViewController { App() }
