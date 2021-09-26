package dev.mkeeda.spaceship.infra.repositoryimpl

import dev.mkeeda.spaceship.data.TimelinePost
import dev.mkeeda.spaceship.data.fakeTimelinePostItems
import dev.mkeeda.spaceship.domain.repository.TimelineRepository
import kotlinx.coroutines.delay

class TimelineRepositoryImpl : TimelineRepository {
    override suspend fun getTimelinePostList(): List<TimelinePost> {
        delay(1000)
        return fakeTimelinePostItems
    }
}
