package dev.mkeeda.spaceship.domain.usecase

import dev.mkeeda.spaceship.data.credential.LoginCredential
import dev.mkeeda.spaceship.domain.repository.LoginCredentialRepository
import dev.mkeeda.spaceship.domain.usecase.base.UseCase
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class LoginWithPassword @Inject constructor(
    private val loginCredentialRepository: LoginCredentialRepository
): UseCase<LoginCredential, Unit>() {
    override fun useCaseFlow(param: LoginCredential): Flow<Unit> {
        return flow {
            loginCredentialRepository.save(newCredential = param)
        }
    }
}
