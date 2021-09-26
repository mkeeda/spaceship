package dev.mkeeda.spaceship.ui.timeline.presentation

import dev.mkeeda.spaceship.data.TimelinePost
import dev.mkeeda.spaceship.data.fakeTimelinePostItems

data class TimelineViewState(
    val postItems: List<TimelinePost>
) {
    companion object {
        val longFake = TimelineViewState(
            postItems = fakeTimelinePostItems + fakeTimelinePostItems + fakeTimelinePostItems
        )
    }
}
