package calldoctor.app_shared.config.data.repository

import calldoctor.app_shared.config.domain.api.AppConfigApi
import calldoctor.app_shared.config.domain.repository.AppConfigRepository

class AppConfigRepositoryImpl(
    private val api: AppConfigApi
): AppConfigRepository {

    override suspend fun loadAppConfig() = api.loadAppConfig()
}