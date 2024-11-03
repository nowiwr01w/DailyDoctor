package calldoctor.app_shared.config.domain.repository

import calldoctor.app_shared.config.config.AppConfig

interface AppConfigRepository {
    suspend fun loadAppConfig(): AppConfig
}