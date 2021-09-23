package dev.mkeeda.spaceship.infra.repositoryimpl

import dev.mkeeda.spaceship.data.kintone.KintoneNotificationList
import dev.mkeeda.spaceship.domain.repository.KintoneNotificationRepository
import kotlinx.coroutines.delay

class KintoneNotificationRepositoryImpl : KintoneNotificationRepository {
    override suspend fun getKintoneNotificationList(): KintoneNotificationList {
        delay(1000)
        return fakeData
    }
}

private val fakeData = KintoneNotificationList(
    hasMore = false,
    ntf = listOf(),
    senders = mapOf(),
    hasIgnoreMention = null
)
