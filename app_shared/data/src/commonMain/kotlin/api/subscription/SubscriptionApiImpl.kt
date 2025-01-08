package api.subscription

import com.nowiwr01p.model.api.errors.subscription.SubscriptionApiError
import com.nowiwr01p.model.api.errors.subscription.SubscriptionApiError.LoadSubscriptionPlansApiError
import com.nowiwr01p.model.api.route.SubscriptionRoutes.GetSubscriptionPlansRoute
import com.nowiwr01p.model.model.subscription.SubscriptionPlan
import com.nowiwr01p.model.resources.language.Language
import nowiwr01p.daily.doctor.base_api_client.api.ApiClientSettings.AppApiClientSettings
import nowiwr01p.daily.doctor.base_api_client.api.BaseApi
import nowiwr01p.daily.doctor.base_api_client.api.base.ApiParameter

class SubscriptionApiImpl: BaseApi<SubscriptionApiError>(AppApiClientSettings), SubscriptionApi {
    /**
     * PLANS
     */
    override suspend fun loadSubscriptionPlans(language: Language) = run {
        getHttp<List<SubscriptionPlan>, LoadSubscriptionPlansApiError>(
            route = GetSubscriptionPlansRoute,
            parameters = listOf(
                ApiParameter(name = "lang", language.code)
            ),
            error = { message -> LoadSubscriptionPlansApiError(message) }
        )
    }
}