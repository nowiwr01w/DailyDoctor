package repository.subscription

import api.subscription.SubscriptionApi
import repository.AppBaseRepository

class AppSubscriptionRepositoryImpl(
    private val api: SubscriptionApi
): AppBaseRepository(), AppSubscriptionRepository {
    /**
     * PLANS
     */
    override suspend fun loadSubscriptionPlans() = io {
        api.loadSubscriptionPlans(language = getAppLanguage())
    }
}