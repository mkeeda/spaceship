package dev.mkeeda.spaceship.ui.login.password

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import dev.mkeeda.spaceship.data.credential.LoginCredential
import dev.mkeeda.spaceship.ui.common.component.SpaceshipAppBar
import dev.mkeeda.spaceship.ui.common.util.PreviewBackground
import dev.mkeeda.spaceship.ui.common.util.UiCommonString
import dev.mkeeda.spaceship.ui.common.util.collectInLaunchedEffect
import dev.mkeeda.spaceship.ui.login.password.presentation.PasswordLoginEffect
import dev.mkeeda.spaceship.ui.login.password.presentation.PasswordLoginEvent
import dev.mkeeda.spaceship.ui.login.password.presentation.PasswordLoginViewModel

@Composable
internal fun PasswordLoginScreen(openMainScreen: () -> Unit) {
    PasswordLoginScreen(
        viewModel = hiltViewModel(),
        onLoginSuccess = openMainScreen
    )
}

@Composable
private fun PasswordLoginScreen(
    viewModel: PasswordLoginViewModel,
    onLoginSuccess: () -> Unit
) {
    viewModel.effect.collectInLaunchedEffect { effect ->
        when (effect) {
            PasswordLoginEffect.NavigateToMain -> onLoginSuccess()
        }
    }
    PasswordLoginScreen(
        onSubmit = { newCredential ->
            viewModel.event(PasswordLoginEvent.Submit(newCredential))
        }
    )
}

@Composable
private fun PasswordLoginScreen(
    onSubmit: (LoginCredential) -> Unit = {}
) {
    Scaffold(
        topBar = {
            SpaceshipAppBar(title = stringResource(id = UiCommonString.PasswordLogin_AppBar_Title))
        }
    ) { contentPadding ->
        LoginCredentialInputForm(
            onSubmit = onSubmit,
            contentPadding = contentPadding
        )
    }
}

@Composable
private fun LoginCredentialInputForm(
    onSubmit: (LoginCredential) -> Unit,
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(state = rememberScrollState())
            .padding(contentPadding)
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        var subdomain by remember {
            mutableStateOf("")
        }
        var username by remember {
            mutableStateOf("")
        }
        var password by remember {
            mutableStateOf("")
        }
        val focusManager = LocalFocusManager.current
        OutlinedTextField(
            value = subdomain,
            onValueChange = { subdomain = it },
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text(text = stringResource(id = UiCommonString.PasswordLogin_SubdomainTextField_Label))
            },
            leadingIcon = {
                Text(
                    text = stringResource(id = UiCommonString.PasswordLogin_Url_Scheme),
                    modifier = Modifier.padding(start = 16.dp)
                )
            },
            trailingIcon = {
                Text(
                    text = stringResource(id = UiCommonString.PasswordLogin_Url_CybozuHost),
                    modifier = Modifier.padding(end = 16.dp)
                )
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Uri,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = {
                    focusManager.moveFocus(FocusDirection.Next)
                }
            ),
            singleLine = true
        )
        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text(text = stringResource(id = UiCommonString.PasswordLogin_UsernameTextField_Label))
            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            keyboardActions = KeyboardActions(
                onNext = {
                    focusManager.moveFocus(FocusDirection.Next)
                }
            ),
            singleLine = true
        )
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text(text = stringResource(id = UiCommonString.PasswordLogin_PasswordTextField_Label))
            },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    focusManager.clearFocus()
                }
            ),
            singleLine = true
        )

        Spacer(modifier = Modifier.width(8.dp))
        Button(
            modifier = Modifier.align(Alignment.End),
            onClick = {
                onSubmit(
                    LoginCredential(
                        domain = "https://$subdomain.cybozu.com",
                        username = username,
                        password = password
                    )
                )
            }
        ) {
            Text(text = stringResource(id = UiCommonString.PasswordLogin_LoginButton_Label))
        }
    }
}

@Preview
@Composable
private fun PasswordLoginScreenPreview() {
    PreviewBackground {
        PasswordLoginScreen()
    }
}
