package dev.mkeeda.spaceship.infra.repositoryimpl

import dev.mkeeda.spaceship.data.credential.LoginCredential
import dev.mkeeda.spaceship.domain.repository.LoginCredentialRepository
import dev.mkeeda.spaceship.infra.datasource.LoginCredentialDataSource
import javax.inject.Inject

class LoginCredentialRepositoryImpl @Inject internal constructor(
    private val loginCredentialDataSource: LoginCredentialDataSource
) : LoginCredentialRepository {
    override suspend fun save(newCredential: LoginCredential) {
        loginCredentialDataSource.writeLoginCredential(newCredential)
    }
}
