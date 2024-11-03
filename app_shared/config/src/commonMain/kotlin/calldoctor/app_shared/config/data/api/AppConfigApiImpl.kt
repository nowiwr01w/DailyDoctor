package calldoctor.app_shared.config.data.api

import calldoctor.app_shared.config.config.AppConfig
import calldoctor.app_shared.config.config.currentAppConfig
import calldoctor.app_shared.config.domain.api.AppConfigApi

class AppConfigApiImpl: AppConfigApi {

    override suspend fun loadAppConfig() = currentAppConfig // TODO: Load from server
}