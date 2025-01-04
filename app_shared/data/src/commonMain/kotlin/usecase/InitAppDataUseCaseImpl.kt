package usecase

import com.nowiwr01p.model.coroutines.app_scope.AppScope
import com.nowiwr01p.model.extensions.runCatchingApp
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import manager.brand_config.AppBrandConfigManager
import manager.onboarding.AppOnboardingManager
import manager.subscription.AppSubscriptionManager

class InitAppDataUseCaseImpl(
    private val appScope: AppScope,
    private val appBrandConfigManager: AppBrandConfigManager,
    private val appOnboardingManager: AppOnboardingManager,
    private val appSubscriptionManager: AppSubscriptionManager
): InitAppDataUseCase {
    /**
     * INIT
     */
    override suspend fun execute(input: Unit) {
        runCatchingApp {
            loadAppData()
        }
    }

    private suspend fun loadAppData() = coroutineScope {
        listOf(
            async { initAppConfig() },
            async { initSubscriptionPlans() }
        ).awaitAll()
    }

    /**
     * APP CONFIG
     */
    private suspend fun initAppConfig() {
        appBrandConfigManager.getBrandConfig(fromRemote = true).collectLatest { config ->
            if (config.brandSettings.isOnboardingEnabled) {
                initOnboardingData()
            }
        }
    }

    /**
     * ONBOARDING DATA
     */
    private fun initOnboardingData() = appScope.scope.launch {
        appOnboardingManager.getOnboardingData(fromRemote = true)
    }

    /**
     * SUBSCRIPTION PLANS
     */
    private suspend fun initSubscriptionPlans() {
        appSubscriptionManager.getSubscriptionPlans(withRemote = true)
    }
}