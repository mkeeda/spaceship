package dev.mkeeda.spaceship

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.core.view.WindowCompat
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint
import dev.mkeeda.spaceship.ui.common.theme.SpaceshipTheme
import dev.mkeeda.spaceship.ui.foundation.AppNavigation
import dev.mkeeda.spaceship.ui.foundation.Screen
import dev.mkeeda.spaceship.ui.foundation.SpaceshipAppBar

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            val systemUiController = rememberSystemUiController()
            val useDarkIcons = MaterialTheme.colors.isLight
            SideEffect {
                systemUiController.setSystemBarsColor(
                    color = Color.Transparent,
                    darkIcons = useDarkIcons
                )
            }
            SpaceshipTheme {
                val navController = rememberNavController()
                val backStackEntry = navController.currentBackStackEntryAsState()
                val currentScreen =
                    Screen.fromRoute(route = backStackEntry.value?.destination?.route)

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
