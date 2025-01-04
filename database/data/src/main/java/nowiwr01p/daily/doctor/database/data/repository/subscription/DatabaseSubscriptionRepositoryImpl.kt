package nowiwr01p.daily.doctor.database.data.repository.subscription

import com.nowiwr01p.model.resources.language.Language
import nowiwr01p.daily.doctor.database.domain.repository.subscription.DatabaseSubscriptionRepository
import nowiwr01p.daily.doctor.database.domain.storage.subscription.DatabaseSubscriptionStorage

class DatabaseSubscriptionRepositoryImpl(
    private val storage: DatabaseSubscriptionStorage
): DatabaseSubscriptionRepository {
    /**
     * PLANS
     */
    override suspend fun getSubscriptionPlans(language: Language) = run {
        storage.getSubscriptionPlans(language)
    }
}