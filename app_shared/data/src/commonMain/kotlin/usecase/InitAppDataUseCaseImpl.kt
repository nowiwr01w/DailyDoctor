package usecase

import com.nowiwr01p.model.extensions.runCatchingApp
import com.nowiwr01p.model.model.app_config.config.BrandConfig
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.first
import manager.brand_config.AppBrandConfigManager
import manager.onboarding.AppOnboardingManager
import manager.subscription.AppSubscriptionManager

class InitAppDataUseCaseImpl(
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
        appBrandConfigManager.getBrandConfig(fromRemote = true).first().let { config ->
            listOf(
                async { initOnboardingData(config) },
                async { initSubscriptionPlans() },
            ).awaitAll()
        }
    }

    /**
     * ONBOARDING DATA
     */
    private suspend fun initOnboardingData(config: BrandConfig) {
        if (config.brandSettings.isOnboardingEnabled) {
            appOnboardingManager.getOnboardingData(fromRemote = true)
        }
    }

    /**
     * SUBSCRIPTION PLANS
     */
    private suspend fun initSubscriptionPlans() {
        appSubscriptionManager.getSubscriptionPlans(withRemote = true)
    }
}