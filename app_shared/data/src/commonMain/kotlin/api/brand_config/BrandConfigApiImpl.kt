package api.brand_config

import com.nowiwr01p.model.api.errors.app_config.AppConfigApiError
import com.nowiwr01p.model.api.errors.app_config.AppConfigApiError.LoadAppConfigApiError
import com.nowiwr01p.model.api.route.BrantConfigRoutes.GetBrandConfigRoute
import com.nowiwr01p.model.model.app_config.config.BrandConfig
import nowiwr01p.daily.doctor.base_api_client.api.ApiClientSettings.AppApiClientSettings
import nowiwr01p.daily.doctor.base_api_client.api.BaseApi
import nowiwr01p.daily.doctor.base_api_client.api.base.ApiParameter

class BrandConfigApiImpl: BaseApi<AppConfigApiError>(AppApiClientSettings), BrandConfigApi {
    /**
     * APP CONFIG
     */
    override suspend fun loadBrandConfig() = getHttp<BrandConfig, LoadAppConfigApiError>(
        route = GetBrandConfigRoute,
        parameters = listOf(
            ApiParameter(name = "key", data = encryptionHelper.initPublicKey())
        ),
        error = { message -> LoadAppConfigApiError(message) }
    )
}
