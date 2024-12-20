package splash

import com.nowiwr01p.model.coroutines.app_scope.AppScope
import com.nowiwr01p.model.usecase.execute
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import manager.brand_config.AppBrandConfigManager
import manager.onboarding.AppOnboardingManager
import splash.SplashContract.Effect
import splash.SplashContract.Effect.NavigateToHome
import splash.SplashContract.Effect.NavigateToOnboarding
import splash.SplashContract.Event
import splash.SplashContract.State
import splash.data.SplashAnimationState
import user.usecase.GetLocalUserUseCase
import view_model.BaseViewModel

class SplashViewModel(
    scope: CoroutineScope,
    private val appScope: AppScope,
    private val getLocalUserUseCase: GetLocalUserUseCase,
    private val appOnboardingManager: AppOnboardingManager,
    private val appBrandConfigManager: AppBrandConfigManager
): BaseViewModel<Event, State, Effect>(scope) {

    override fun setInitialState() = State()

    override fun handleEvents(event: Event) {
        when (event) {
            is Event.Init -> init()
        }
    }

    private fun init() {
        startTimer()
        getBrandConfig()
    }

    /**
     * GET BRAND ONBOARDING ITEMS
     */
    private fun getBrandConfig() = appScope.scope.launch { // TODO: Check
        appBrandConfigManager.getBrandConfig(fromRemote = false).collect { config ->
            if (config.brandSettings.isOnboardingEnabled) {
                loadOnboardingData()
            }
        }
    }

    private fun loadOnboardingData() = appScope.scope.launch {
        appOnboardingManager.getOnboardingData(fromRemote = true)
    }

    /**
     * ANIMATION
     */
    private fun startTimer() = hide {
        val animationDuration = SplashAnimationState.entries
            .last()
            .showUntilAtMillis
        (0..animationDuration step 100).asSequence()
            .asFlow()
            .onEach { millis ->
                setAnimationState(millis)
                delay(100)
            }
            .onCompletion {
                chooseNavigationDestination()
            }
            .collect()
    }

    private fun setAnimationState(millis: Int) = setState {
        val animationState = SplashAnimationState.entries.first { item ->
            millis <= item.showUntilAtMillis
        }
        copy(animationState = animationState)
    }

    /**
     * NAVIGATION
     */
    private suspend fun chooseNavigationDestination() {
        val effect = when {
            getLocalUserUseCase.execute() != null -> NavigateToHome
            else -> NavigateToOnboarding
        }
        setEffect { effect }
    }
}
