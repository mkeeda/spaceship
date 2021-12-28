package dev.mkeeda.spaceship.ui.timeline.presentation

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.android.components.ActivityComponent
import dev.mkeeda.spaceship.ui.common.viewmodel.assistedViewModel

@EntryPoint
@InstallIn(ActivityComponent::class)
interface PostDetailsViewModelEntryPoint {
    fun postDetailsViewModelFactory(): PostDetailsViewModel.Factory
}

@Composable
fun postDetailsViewModel(postId: Long): PostDetailsViewModel {
    val factory = EntryPointAccessors.fromActivity(
        LocalContext.current as Activity,
        PostDetailsViewModelEntryPoint::class.java
    ).postDetailsViewModelFactory()
    return assistedViewModel {
        factory.create(postId = postId)
    }
}
