package api.subscription

import com.nowiwr01p.model.model.subscription.plan.SubscriptionPlan
import nowiwr01p.daily.doctor.base_api_client.api.Api

interface SubscriptionApi: Api {
    suspend fun loadSubscriptionPlans(): List<SubscriptionPlan>
}