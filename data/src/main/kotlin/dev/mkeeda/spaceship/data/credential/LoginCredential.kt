package dev.mkeeda.spaceship.data.credential

import kotlinx.serialization.Serializable

fun LoginCredential(
    loginOrigin: String,
    username: String,
    password: String,
): LoginCredential = LoginCredential(
    environmentConfig = EnvironmentConfig(loginOrigin = loginOrigin),
    username = username,
    password = password
)

@Serializable
data class LoginCredential(
    val environmentConfig: EnvironmentConfig,
    val username: String,
    val password: String,
) {
    companion object {
        val None = LoginCredential(
            environmentConfig = EnvironmentConfig(loginOrigin = ""),
            username = "",
            password = ""
        )
    }
}

@Serializable
data class EnvironmentConfig(
    val loginOrigin: String,
    val secureAccessConfig: SecureAccessConfig? = null
) {
    fun isSecureAccess(): Boolean {
        val secureAccessOriginPattern = Regex("""https://[\w]+.s.cybozu.com""")
        return secureAccessOriginPattern.matches(loginOrigin)
    }
}

@Serializable
data class SecureAccessConfig(
    val clientCertBase64: String,
    val clientCertPassword: String
)

inline fun EnvironmentConfig.ifSecureAccess(block: (SecureAccessConfig) -> Unit) {
    if (isSecureAccess()) {
        checkNotNull(secureAccessConfig) {
            "secureAccessConfig must be not null when origin url includes '.s' path."
        }
        block(secureAccessConfig)
    }
}
