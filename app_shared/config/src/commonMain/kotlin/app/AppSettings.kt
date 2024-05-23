package config.app

sealed interface AppSettings {
    val isDebugMode: Boolean
}

/**
 * DEBUG
 */
data class DebugAppSettings(
    override val isDebugMode: Boolean = true
) : AppSettings

/**
 * RELEASE
 */
data class ReleaseAppSettings(
    override val isDebugMode: Boolean = false
) : AppSettings