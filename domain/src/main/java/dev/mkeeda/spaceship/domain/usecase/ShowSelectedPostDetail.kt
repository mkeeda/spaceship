package dev.mkeeda.spaceship.domain.usecase

import dev.mkeeda.spaceship.data.PostId
import dev.mkeeda.spaceship.data.TimelinePostDetail
import dev.mkeeda.spaceship.domain.repository.TimelineRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ShowSelectedPostDetail @Inject constructor(
    private val timelineRepository: TimelineRepository,
) : UseCase<PostId, TimelinePostDetail>() {
    override fun useCaseFlow(param: PostId): Flow<TimelinePostDetail> {
        return flow {
            val selectedPostDetail = timelineRepository.getTimelinePost(postId = param)
            emit(selectedPostDetail)
        }
    }
}
