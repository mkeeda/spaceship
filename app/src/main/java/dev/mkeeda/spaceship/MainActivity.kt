package dev.mkeeda.spaceship

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Scaffold
import androidx.core.view.WindowCompat
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.insets.ProvideWindowInsets
import dev.mkeeda.spaceship.ui.foundation.AppNavigation
import dev.mkeeda.spaceship.ui.foundation.Screen
import dev.mkeeda.spaceship.ui.foundation.SpaceshipAppBar
import dev.mkeeda.spaceship.ui.theme.SpaceshipTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            SpaceshipTheme {
                ProvideWindowInsets {
                    val navController = rememberNavController()
                    val backStackEntry = navController.currentBackStackEntryAsState()
                    val currentScreen = Screen.fromRoute(route = backStackEntry.value?.destination?.route)

                    Scaffold(
                        topBar = {
                            SpaceshipAppBar(currentScreen = currentScreen)
                        }
                    ) {
                        AppNavigation(navHostController = navController)
                    }
                }
            }
        }
    }
}
