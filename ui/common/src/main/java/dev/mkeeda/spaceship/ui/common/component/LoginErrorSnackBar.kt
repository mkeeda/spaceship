package dev.mkeeda.spaceship.ui.common.component

import android.content.Context
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.SnackbarResult
import androidx.paging.LoadState
import dev.mkeeda.spaceship.data.credential.NoLoginCredentialException
import dev.mkeeda.spaceship.ui.common.R

fun LoadState.loginErrorOrNull(): NoLoginCredentialException? {
    return if (this is LoadState.Error && error is NoLoginCredentialException) {
        error as NoLoginCredentialException
    } else {
        null
    }
}

suspend fun SnackbarHostState.showLoginErrorSnackBar(
    context: Context,
    onLoginButtonClicked: () -> Unit
) {
    val result = showSnackbar(
        message = context.getString(R.string.LoginError_Snackbar_Message),
        actionLabel = context.getString(R.string.LoginError_Snackbar_Action),
        duration = SnackbarDuration.Indefinite
    )

    if (result == SnackbarResult.ActionPerformed) {
        onLoginButtonClicked()
    }
}
