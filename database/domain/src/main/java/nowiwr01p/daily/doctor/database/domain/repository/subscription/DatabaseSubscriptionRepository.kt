package nowiwr01p.daily.doctor.database.domain.repository.subscription

import com.nowiwr01p.model.model.subscription.SubscriptionPlan

interface DatabaseSubscriptionRepository {
    suspend fun getSubscriptionPlans(): List<SubscriptionPlan>
}