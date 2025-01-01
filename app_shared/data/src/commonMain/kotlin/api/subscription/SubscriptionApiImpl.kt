package api.subscription

import com.nowiwr01p.model.api.route.SubscriptionRoutes.GetSubscriptionPlansRoute
import com.nowiwr01p.model.model.subscription.SubscriptionPlan
import nowiwr01p.daily.doctor.base_api_client.api.ApiClientSettings.AppApiClientSettings
import nowiwr01p.daily.doctor.base_api_client.api.BaseApi

class SubscriptionApiImpl: BaseApi(AppApiClientSettings), SubscriptionApi {
    /**
     * PLANS
     */
    override suspend fun loadSubscriptionPlans() = run {
        getHttp<List<SubscriptionPlan>>(route = GetSubscriptionPlansRoute)
    }
}