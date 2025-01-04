package repository.subscription

import api.subscription.SubscriptionApi
import com.nowiwr01p.model.repository.BaseRepository

class AppSubscriptionRepositoryImpl(
    private val api: SubscriptionApi
): BaseRepository(), AppSubscriptionRepository {
    /**
     * PLANS
     */
    override suspend fun loadSubscriptionPlans() = io {
        api.loadSubscriptionPlans()
    }
}