package repository.subscription

import com.nowiwr01p.model.model.subscription.SubscriptionPlan

interface AppSubscriptionRepository {
    suspend fun loadSubscriptionPlans(): List<SubscriptionPlan>
}