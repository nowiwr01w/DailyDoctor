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
import org.koin.core.qualifier.named
import org.koin.dsl.module

val moduleConfig = module {
    /**
     * APP CONFIG
     */
    single<AppConfig>(named(LOCAL)) {
        AppConfigImpl(
            appSettings = get<AppSettings>(named(LOCAL)),
            serverSettings = get<ServerSettings>(named(LOCAL))
        )
    }
    single<AppConfig>(named(REMOTE)) {
        AppConfigImpl(
            appSettings = get<AppSettings>(named(REMOTE)),
            serverSettings = get<ServerSettings>(named(REMOTE))
        )
    }

    /**
     * APP SETTINGS
     */
    single<AppSettings>(named(LOCAL)) {
        DebugAppSettings()
    }
    single<AppSettings>(named(REMOTE)) {
        ReleaseAppSettings()
    }

    /**
     * SERVER SETTINGS
     */
    single<ServerSettings>(named(LOCAL)) {
        DebugServerSettings()
    }
    single<ServerSettings>(named(REMOTE)) {
        ReleaseServerSettings()
    }
}