package dev.mkeeda.spaceship.data.kintone

import kotlinx.serialization.Serializable

@Serializable
data class KintoneUser(
    val id: Long,
    val entityType: String,
    val code: String,
    val name: String,
    val givenName: String,
    val surName: String,
    val givenNameReading: String?,
    val surNameReading: String?,
    val photo: PhotoUrlContainer,
    val email: String,
    val employeeNumber: String
)
