package repository.subscription

import com.nowiwr01p.model.model.subscription.plan.SubscriptionPlan

interface AppSubscriptionRepository {
    suspend fun loadSubscriptionPlans(): List<SubscriptionPlan>
}