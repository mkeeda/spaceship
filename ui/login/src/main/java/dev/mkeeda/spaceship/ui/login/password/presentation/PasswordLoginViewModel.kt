package dev.mkeeda.spaceship.ui.login.password.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.mkeeda.spaceship.data.credential.LoginCredential
import dev.mkeeda.spaceship.domain.usecase.LoadSecureAccessFile
import dev.mkeeda.spaceship.domain.usecase.LoginWithPassword
import javax.inject.Inject
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class PasswordLoginViewModel @Inject constructor(
    private val loginWithPassword: LoginWithPassword,
    private val loadSecureAccessFile: LoadSecureAccessFile
) : ViewModel() {
    private val eventFlow = MutableSharedFlow<PasswordLoginEvent>()

    private val effectChannel = Channel<PasswordLoginEffect>()
    val effect: Flow<PasswordLoginEffect> = effectChannel.receiveAsFlow()

    private val _viewState = MutableStateFlow(PasswordLoginViewState.Empty)
    val viewState: StateFlow<PasswordLoginViewState> = _viewState.asStateFlow()

    init {
        eventFlow.onEach { event ->
            when (event) {
                is PasswordLoginEvent.Submit -> {
                    // TODO: Load client cert
                    _viewState.update { prev ->
                        prev.copy(clientCertFileName = "test_user.cybozusetting")
                    }
                    val loginCredential = LoginCredential(
                        loginOrigin = event.formState.loginOrigin,
                        username = event.formState.username,
                        password = event.formState.password
                    )
                    loginWithPassword.execute(param = loginCredential)
                }
            }
        }.launchIn(viewModelScope)

        loginWithPassword.output.onEach {
            effectChannel.send(PasswordLoginEffect.NavigateToMain)
        }.launchIn(viewModelScope)
    }

    fun event(event: PasswordLoginEvent) {
        viewModelScope.launch {
            eventFlow.emit(event)
        }
    }
}
