import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DrawerContent() {
    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Top) {
        Text(text = "Asetukset", modifier = Modifier.padding(16.dp).clickable { /* Handle click */ })
        Text(text = "Juomat", modifier = Modifier.padding(16.dp).clickable { /* Handle click */ })
        Text(text = "Tilastot", modifier = Modifier.padding(16.dp).clickable { /* Handle click */ })
        // Add more items or custom drawer content here
    }
}