package api.subscription

import com.nowiwr01p.model.api.route.SubscriptionRoutes.GetSubscriptionPlansRoute
import com.nowiwr01p.model.model.subscription.SubscriptionPlan
import com.nowiwr01p.model.resources.language.Language
import nowiwr01p.daily.doctor.base_api_client.api.ApiClientSettings.AppApiClientSettings
import nowiwr01p.daily.doctor.base_api_client.api.BaseApi
import nowiwr01p.daily.doctor.base_api_client.api.base.ApiParameter

class SubscriptionApiImpl: BaseApi(AppApiClientSettings), SubscriptionApi {
    /**
     * PLANS
     */
    override suspend fun loadSubscriptionPlans(language: Language) = run {
        getHttp<List<SubscriptionPlan>>(
            route = GetSubscriptionPlansRoute,
            parameters = listOf(
                ApiParameter(name = "lang", language.code),
            )
        )
    }
}