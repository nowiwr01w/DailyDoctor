package nowiwr01p.daily.doctor.database.domain.storage.subscription

import com.nowiwr01p.model.model.subscription.SubscriptionPlan
import com.nowiwr01p.model.resources.language.Language

interface DatabaseSubscriptionStorage {
    suspend fun getSubscriptionPlans(language: Language): List<SubscriptionPlan>
}