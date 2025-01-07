package api.onboarding

import com.nowiwr01p.model.api.route.OnboardingRoutes.GetOnboardingDataRoute
import com.nowiwr01p.model.model.onboarding.OnboardingItem
import com.nowiwr01p.model.resources.language.Language
import nowiwr01p.daily.doctor.base_api_client.api.ApiClientSettings.AppApiClientSettings
import nowiwr01p.daily.doctor.base_api_client.api.BaseApi
import nowiwr01p.daily.doctor.base_api_client.api.base.ApiParameter

class OnboardingApiImpl: BaseApi(AppApiClientSettings), OnboardingApi {

    override suspend fun getOnboardingData(language: Language) = getHttp<List<OnboardingItem>>(
        route = GetOnboardingDataRoute,
        parameters = listOf(
            ApiParameter(name = "lang", data = language.code)
        )
    )
}