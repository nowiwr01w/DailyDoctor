package nowiwr01p.daily.doctor.server.domain.repository.subscription

import com.nowiwr01p.model.model.subscription.SubscriptionPlan
import com.nowiwr01p.model.resources.language.Language

interface ServerSubscriptionRepository {
    suspend fun getSubscriptionPlans(language: Language): List<SubscriptionPlan>
}