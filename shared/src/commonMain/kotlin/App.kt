import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable

@Composable
fun App() {
    MaterialTheme {
        Scaffold(topBar = {
            TopAppBar(title = { Text("Kaljakello") })
        }, drawerContent = {
            DrawerContent()
        }) { innerPadding ->
            // This is where your main screen content goes
            MainScreen(innerPadding)
        }
    }
}

expect fun getPlatformName(): String