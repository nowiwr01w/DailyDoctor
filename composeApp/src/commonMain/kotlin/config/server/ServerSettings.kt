package config.server

import Platform.ANDROID
import platform

sealed interface ServerSettings {
    val serverUrl: String
}

/**
 * DEBUG
 */
data class DebugServerSettings(
    override val serverUrl: String = when (platform) {
        ANDROID -> "http://10.0.2.2:8081"
        else -> "http://127.0.0.1:8081"
    }
) : ServerSettings

/**
 * RELEASE
 */
data class ReleaseServerSettings(
    override val serverUrl: String = ""
) : ServerSettings