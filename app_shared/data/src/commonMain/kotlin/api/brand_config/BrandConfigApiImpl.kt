package api.brand_config

import com.nowiwr01p.model.api.route.BrantConfigRoutes.GetBrandConfigRoute
import com.nowiwr01p.model.model.app_config.config.BrandConfig
import com.nowiwr01p.model.model.app_config.config.BrandConfigType.CALL_DOCTOR_CONFIG_TYPE
import nowiwr01p.daily.doctor.base_api_client.api.ApiClientSettings.AppApiClientSettings
import nowiwr01p.daily.doctor.base_api_client.api.BaseApi
import nowiwr01p.daily.doctor.base_api_client.api.base.ApiParameter

class BrandConfigApiImpl: BaseApi(AppApiClientSettings), BrandConfigApi {

    override suspend fun loadBrandConfig() = getHttp<BrandConfig>(
        route = GetBrandConfigRoute,
        parameters = listOf(
            ApiParameter("type", currentBrandConfigType.type)
        )
    )
}

// TODO: Release related
internal val currentBrandConfigType = CALL_DOCTOR_CONFIG_TYPE