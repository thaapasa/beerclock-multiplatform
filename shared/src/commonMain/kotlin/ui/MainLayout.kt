package fi.tuska.beerclock.common.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.DrawerState
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import kotlinx.coroutines.launch

@Composable
fun MainLayout(content: @Composable (PaddingValues) -> Unit) {
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()
    val navigator = LocalNavigator.currentOrThrow
    MaterialTheme {
        Scaffold(scaffoldState = scaffoldState, topBar = {
            TopAppBar(title = { Text(strings.appName) }, actions = {
                ToggleDrawerButton(scaffoldState.drawerState)
            })
        }, drawerContent = {
            DrawerContent(selectScreen = {
                coroutineScope.launch {
                    scaffoldState.drawerState.close()
                    navigator.push(it)
                }
            })
        }, drawerGesturesEnabled = true, content = content
        )
    }
}

@Composable
fun ToggleDrawerButton(drawerState: DrawerState) {
    val coroutineScope = rememberCoroutineScope()
    IconButton(onClick = {
        coroutineScope.launch {
            if (drawerState.isOpen) {
                drawerState.close()
            } else {
                drawerState.open()
            }
        }
    }) {
        Icon(
            imageVector = Icons.Default.Menu,
            contentDescription = strings.menu.main,
        )
    }
}
