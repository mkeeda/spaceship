package dev.mkeeda.spaceship.infra.repositoryimpl

import android.net.Uri
import dev.mkeeda.spaceship.data.credential.SecureAccessFile
import dev.mkeeda.spaceship.domain.repository.SecureAccessFileRepository
import dev.mkeeda.spaceship.infra.datasource.SecureAccessFileDataSource
import javax.inject.Inject

class SecureAccessFileRepositoryImpl @Inject constructor(
    private val secureAccessFileDataSource: SecureAccessFileDataSource
) : SecureAccessFileRepository {
    override suspend fun getSecureAccessFile(filePath: String): SecureAccessFile {
        val secureAccessFilePath = Uri.parse(filePath)
        return SecureAccessFile(
            name = secureAccessFileDataSource.getFileName(secureAccessFilePath),
            config = secureAccessFileDataSource.load(secureAccessFilePath)
        )
    }
}
