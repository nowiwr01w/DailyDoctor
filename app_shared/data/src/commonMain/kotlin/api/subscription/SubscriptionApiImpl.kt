package api.subscription

import com.nowiwr01p.model.api.route.SubscriptionRoutes.GetSubscriptionPlansRoute
import com.nowiwr01p.model.model.app_config.config.BrandConfigType
import com.nowiwr01p.model.model.subscription.plan.SubscriptionPlan
import nowiwr01p.daily.doctor.base_api_client.api.ApiClientSettings.AppApiClientSettings
import nowiwr01p.daily.doctor.base_api_client.api.BaseApi
import nowiwr01p.daily.doctor.base_api_client.api.base.ApiParameter
import com.nowiwr01p.model.resources.language.Language

class SubscriptionApiImpl: BaseApi(AppApiClientSettings), SubscriptionApi {
    /**
     * PLANS
     */
    override suspend fun loadSubscriptionPlans() = run {
        getHttp<List<SubscriptionPlan>>(
            route = GetSubscriptionPlansRoute,
            parameters = listOf(
                ApiParameter(name = "lang", Language.Ukrainian.code), // TODO: Move this logic to BaseApi and send it via headers
                ApiParameter(name = "type", BrandConfigType.CALL_DOCTOR_CONFIG_TYPE.type)
            )
        )
    }
}