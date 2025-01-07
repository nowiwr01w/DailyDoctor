package nowiwr01p.daily.doctor.server.data.repository.subscription

import com.nowiwr01p.model.repository.BaseRepository
import com.nowiwr01p.model.resources.language.Language
import nowiwr01p.daily.doctor.database.domain.repository.subscription.DatabaseSubscriptionRepository
import nowiwr01p.daily.doctor.server.domain.repository.subscription.ServerSubscriptionRepository

class ServerSubscriptionRepositoryImpl(
    private val repository: DatabaseSubscriptionRepository
): BaseRepository(), ServerSubscriptionRepository {
    /**
     * PLANS
     */
    override suspend fun getSubscriptionPlans(language: Language) = io {
        repository.getSubscriptionPlans(language)
    }
}