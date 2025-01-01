package nowiwr01p.daily.doctor.server.domain.repository.subscription

import com.nowiwr01p.model.model.subscription.SubscriptionPlan

interface ServerSubscriptionRepository {
    suspend fun getSubscriptionPlans(): List<SubscriptionPlan>
}