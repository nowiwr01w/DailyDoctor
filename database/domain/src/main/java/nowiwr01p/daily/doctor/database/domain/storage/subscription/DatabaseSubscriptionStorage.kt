package nowiwr01p.daily.doctor.database.domain.storage.subscription

import com.nowiwr01p.model.model.subscription.SubscriptionPlan

interface DatabaseSubscriptionStorage {
    suspend fun getSubscriptionPlans(): List<SubscriptionPlan>
}