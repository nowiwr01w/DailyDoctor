package manager.subscription

import com.nowiwr01p.model.model.subscription.SubscriptionPlan
import kotlinx.coroutines.flow.Flow

interface AppSubscriptionManager {
    suspend fun getSubscriptionPlans(withRemote: Boolean): Flow<List<SubscriptionPlan>>
}