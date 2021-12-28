package dev.mkeeda.spaceship.ui.timeline.presentation

import dev.mkeeda.spaceship.data.PostingLocation

sealed class PostDetailsEvent {
    data class ShowContext(val location: PostingLocation) : PostDetailsEvent()
}
