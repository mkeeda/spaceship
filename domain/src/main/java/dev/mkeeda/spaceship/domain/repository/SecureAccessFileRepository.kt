package dev.mkeeda.spaceship.domain.repository

import dev.mkeeda.spaceship.data.credential.SecureAccessFile

interface SecureAccessFileRepository {
    suspend fun getSecureAccessFile(filePath: String): SecureAccessFile
}
