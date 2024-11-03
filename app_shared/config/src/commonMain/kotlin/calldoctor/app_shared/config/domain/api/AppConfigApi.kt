package calldoctor.app_shared.config.domain.api

import calldoctor.app_shared.config.config.AppConfig

interface AppConfigApi {
    suspend fun loadAppConfig(): AppConfig
}