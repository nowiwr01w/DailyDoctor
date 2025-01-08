package manager.subscription

import com.nowiwr01p.model.extensions.runCatchingApp
import com.nowiwr01p.model.model.subscription.SubscriptionPlan
import com.nowiwr01p.model.usecase.execute
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import usecase.subscription.AppGetSubscriptionPlansUseCase

class AppSubscriptionManagerImpl(
    private val getSubscriptionPlansUseCase: AppGetSubscriptionPlansUseCase
): AppSubscriptionManager {
    /**
     * DATA
     */
    private val plans = MutableStateFlow<List<SubscriptionPlan>>(emptyList())

    /**
     * PLANS
     */
    override suspend fun getSubscriptionPlans(withRemote: Boolean): Flow<List<SubscriptionPlan>> {
        if (withRemote || plans.value.isEmpty()) {
            loadSubscriptionPlans()
        }
        return plans
    }

    private suspend fun loadSubscriptionPlans() = runCatchingApp {
        getSubscriptionPlansUseCase.execute()
    }.onSuccess { remotePlans ->
        plans.emit(remotePlans)
    }
}