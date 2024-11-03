package calldoctor.app_shared.config.settings.server

import platform.Platform.ANDROID
import platform.currentPlatform

sealed interface ServerSettings {
    val serverUrl: String
}

/**
 * DEBUG
 */
data class ServerSettingsDebug(
    override val serverUrl: String = when (currentPlatform) {
        ANDROID -> "http://10.0.2.2:8081"
        else -> "http://127.0.0.1:8081"
    }
) : ServerSettings

/**
 * RELEASE
 */
data class ServerSettingsRelease(
    override val serverUrl: String
) : ServerSettings