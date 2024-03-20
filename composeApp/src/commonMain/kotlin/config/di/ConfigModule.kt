package config.di

import config.AppBuildType.LOCAL
import config.AppBuildType.REMOTE
import config.AppConfig
import config.AppConfigImpl
import config.app.AppSettings
import config.app.DebugAppSettings
import config.app.ReleaseAppSettings
import config.server.DebugServerSettings
import config.server.ReleaseServerSettings
import config.server.ServerSettings
import org.kodein.di.DI
import org.kodein.di.bindSingleton
import org.kodein.di.instance

val moduleConfig = DI.Module("ConfigModule") {
    /**
     * APP CONFIG
     */
    bindSingleton<AppConfig>(LOCAL) {
        AppConfigImpl(
            appSettings = instance<AppSettings>(LOCAL),
            serverSettings = instance<ServerSettings>(LOCAL)
        )
    }
    bindSingleton<AppConfig>(REMOTE) {
        AppConfigImpl(
            appSettings = instance<AppSettings>(REMOTE),
            serverSettings = instance<ServerSettings>(REMOTE)
        )
    }

    /**
     * APP SETTINGS
     */
    bindSingleton<AppSettings>(LOCAL) {
        DebugAppSettings()
    }
    bindSingleton<AppSettings>(REMOTE) {
        ReleaseAppSettings()
    }

    /**
     * SERVER SETTINGS
     */
    bindSingleton<ServerSettings>(LOCAL) {
        DebugServerSettings()
    }
    bindSingleton<ServerSettings>(REMOTE) {
        ReleaseServerSettings()
    }
}