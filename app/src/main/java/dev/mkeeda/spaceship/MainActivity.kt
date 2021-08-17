package dev.mkeeda.spaceship

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import dev.mkeeda.spaceship.ui.AppNavigation
import dev.mkeeda.spaceship.ui.theme.SpaceshipTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SpaceshipTheme {
                AppNavigation(navHostController = rememberNavController())
            }
        }
    }
}
