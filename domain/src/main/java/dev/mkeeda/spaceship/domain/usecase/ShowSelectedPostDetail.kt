package dev.mkeeda.spaceship.domain.usecase

import dev.mkeeda.spaceship.data.PostId
import dev.mkeeda.spaceship.data.TimelinePostDetail
import dev.mkeeda.spaceship.domain.repository.PostDetailsRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ShowSelectedPostDetail @Inject constructor(
    private val postDetailsRepository: PostDetailsRepository,
) : UseCase<PostId, TimelinePostDetail>() {
    override fun useCaseFlow(param: PostId): Flow<TimelinePostDetail> {
        return flow {
            val selectedPostDetail = postDetailsRepository.getPostDetail(postId = param)
            emit(selectedPostDetail)
        }
    }
}
