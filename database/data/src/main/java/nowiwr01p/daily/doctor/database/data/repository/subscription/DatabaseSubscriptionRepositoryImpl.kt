package nowiwr01p.daily.doctor.database.data.repository.subscription

import com.nowiwr01p.model.model.app_config.config.BrandConfigType
import nowiwr01p.daily.doctor.database.domain.repository.subscription.DatabaseSubscriptionRepository
import nowiwr01p.daily.doctor.database.domain.storage.subscription.DatabaseSubscriptionStorage
import com.nowiwr01p.model.resources.language.Language

class DatabaseSubscriptionRepositoryImpl(
    private val storage: DatabaseSubscriptionStorage
): DatabaseSubscriptionRepository {
    /**
     * PLANS
     */
    override suspend fun getSubscriptionPlans(brand: BrandConfigType, language: Language) = run {
        storage.getSubscriptionPlans(
            brand = brand,
            language = language
        )
    }
}