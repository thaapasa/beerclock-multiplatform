package fi.tuska.beerclock.common

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.Navigator
import fi.tuska.beerclock.common.ui.MainLayout
import fi.tuska.beerclock.common.ui.MainScreen

@Composable
fun App() {
    Navigator(
        screen = HomeScreen
    )
}

object HomeScreen : Screen {

    @Composable
    override fun Content() {
        MainLayout(content = { MainScreen() })
    }
}

object SettingsScreen : Screen {

    @Composable
    override fun Content() {
        MainLayout(content = { Text(text = "Settings") })
    }
}

object DrinksScreen : Screen {

    @Composable
    override fun Content() {
        MainLayout(content = { Text(text = "Drinks") })
    }
}

object StatisticsScreen : Screen {

    @Composable
    override fun Content() {
        MainLayout(content = { Text(text = "Statistics") })
    }
}

expect fun getPlatformName(): String
