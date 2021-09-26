package dev.mkeeda.spaceship.domain.usecase

import dev.mkeeda.spaceship.data.kintone.KintoneNotificationList
import dev.mkeeda.spaceship.domain.repository.KintoneNotificationRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ObserveKintoneNotification @Inject constructor(
    private val repository: KintoneNotificationRepository
) {
    fun execute(): Flow<DomainLoadState<KintoneNotificationList>> {
        return flow {
            val notificationList = repository.getKintoneNotificationList()
            emit(notificationList)
        }.toLoadState()
    }
}
