package dev.mkeeda.spaceship

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dev.mkeeda.spaceship.data.fakeTimeLinePostItems
import dev.mkeeda.spaceship.ui.theme.SpaceshipTheme
import dev.mkeeda.spaceship.ui.timeline.TimeLineScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SpaceshipTheme {
                TimeLineScreen(postItems = fakeTimeLinePostItems)
            }
        }
    }
}
