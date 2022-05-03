package dev.mkeeda.spaceship.ui.common.util

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import kotlinx.coroutines.flow.Flow

/**
 * https://github.com/DroidKaigi/conference-app-2021/blob/3fb47a7b7b245e5a7c7c66d6c18cb7d2a7b295f2/uicomponent-compose/core/src/main/java/io/github/droidkaigi/feeder/core/util/Flows.kt
 */
@SuppressLint("ComposableNaming")
@Composable
fun <T> Flow<T>.collectInLaunchedEffect(collector: suspend (value: T) -> Unit) {
    val flow = this
    LaunchedEffect(flow) {
        flow.collect {
            collector(it)
        }
    }
}
