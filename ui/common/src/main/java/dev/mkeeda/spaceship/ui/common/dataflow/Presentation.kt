package dev.mkeeda.spaceship.ui.common.dataflow

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

/**
 * @see <a href="https://codingtroops.com/android/compose-architecture-part-1-mvvm-or-mvi-architecture-with-flow/">Compose architecture: MVVM or MVI with Flow?</a>
 */
interface Presentation<State, Event, Effect> {
    /**
     * [state] is view state of screen, represents displayed data.
     */
    val state: StateFlow<State>

    /**
     * [event] represents user actions (e.g. click event of a button).
     */
    fun event(event: Event)

    /**
     * [effect] represents a side-effect action that should be consumed by the UI only once.
     */
    val effect: Flow<Effect>
}
