package dev.mkeeda.spaceship.ui.timeline.presentation

import dev.mkeeda.spaceship.data.Conversation
import dev.mkeeda.spaceship.data.fakeConversation

data class PostDetailsViewState(
    val conversation: Conversation? = null
) {
    companion object {
        val Initial = PostDetailsViewState()

        val fake = PostDetailsViewState(
            conversation = fakeConversation
        )
    }
}
