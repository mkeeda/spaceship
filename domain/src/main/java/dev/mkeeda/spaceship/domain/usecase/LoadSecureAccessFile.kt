package dev.mkeeda.spaceship.domain.usecase

import dev.mkeeda.spaceship.data.credential.SecureAccessFile
import dev.mkeeda.spaceship.domain.repository.SecureAccessFileRepository
import dev.mkeeda.spaceship.domain.usecase.base.UseCase
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class LoadSecureAccessFile @Inject constructor(
    private val secureAccessFileRepository: SecureAccessFileRepository
) : UseCase<String, SecureAccessFile>() {
    override fun useCaseFlow(param: String): Flow<SecureAccessFile> {
        return flow {
            secureAccessFileRepository.getSecureAccessFile(filePath = param)
        }
    }
}
