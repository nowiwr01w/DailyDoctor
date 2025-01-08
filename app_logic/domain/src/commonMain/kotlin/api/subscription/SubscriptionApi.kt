package api.subscription

import com.nowiwr01p.model.model.subscription.SubscriptionPlan
import com.nowiwr01p.model.resources.language.Language
import nowiwr01p.daily.doctor.base_api_client.api.Api

interface SubscriptionApi: Api {
    suspend fun loadSubscriptionPlans(language: Language): List<SubscriptionPlan>
}