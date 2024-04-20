package config

import config.app.AppSettings
import config.server.ServerSettings

sealed interface AppConfig {
    val appSettings: AppSettings
    val serverSettings: ServerSettings
}

data class AppConfigImpl(
    override val appSettings: AppSettings,
    override val serverSettings: ServerSettings
): AppConfig