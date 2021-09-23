package dev.mkeeda.spaceship.domain.repository

import dev.mkeeda.spaceship.data.kintone.KintoneNotificationList

interface KintoneNotificationRepository {
    suspend fun getKintoneNotificationList(): KintoneNotificationList
}
