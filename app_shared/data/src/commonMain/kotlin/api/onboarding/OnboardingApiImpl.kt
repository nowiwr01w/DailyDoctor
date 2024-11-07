package api.onboarding

import api.brand_config.currentBrandConfigType
import com.nowiwr01p.model.api.route.OnboardingRoutes.GetOnboardingDataRoute
import com.nowiwr01p.model.model.onboarding.OnboardingItemModel
import nowiwr01p.daily.doctor.base_api_client.api.ApiClientSettings.AppApiClientSettings
import nowiwr01p.daily.doctor.base_api_client.api.BaseApi
import nowiwr01p.daily.doctor.base_api_client.api.base.ApiParameter

class OnboardingApiImpl: BaseApi(AppApiClientSettings), OnboardingApi {

    override suspend fun getOnboardingData() = getHttp<List<OnboardingItemModel>>(
        route = GetOnboardingDataRoute,
        parameters = listOf(
            ApiParameter(name = "type", data = currentBrandConfigType.type)
        )
    )
}