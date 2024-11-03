package calldoctor.app_shared.config.brand

import calldoctor.app_shared.config.settings.app.AppSettings
import calldoctor.app_shared.config.settings.server.ServerSettings

interface AppBrand {
    val appSettings: AppSettings
    val serverSettings: ServerSettings
}