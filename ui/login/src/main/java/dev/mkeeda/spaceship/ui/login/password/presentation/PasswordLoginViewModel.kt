package dev.mkeeda.spaceship.ui.login.password.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.mkeeda.spaceship.domain.usecase.LoginWithPassword
import javax.inject.Inject
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

@HiltViewModel
class PasswordLoginViewModel @Inject constructor(
    private val loginWithPassword: LoginWithPassword
) : ViewModel() {
    private val eventFlow = MutableSharedFlow<PasswordLoginEvent>()

    private val effectChannel = Channel<PasswordLoginEffect>()
    val effect: Flow<PasswordLoginEffect> = effectChannel.receiveAsFlow()

    init {
        eventFlow.onEach { event ->
            when (event) {
                is PasswordLoginEvent.Submit -> loginWithPassword.execute(param = event.loginCredential)
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
