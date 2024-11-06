package onboarding

import view_model.BaseViewModel
import kotlinx.coroutines.CoroutineScope
import onboarding.OnboardingContract.*
import manager.onboarding.AppOnboardingManager
import onboarding.OnboardingContract.Effect.*
import onboarding.OnboardingContract.Event.*

class OnboardingViewModel(
    scope: CoroutineScope,
    private val appOnboardingManager: AppOnboardingManager
) : BaseViewModel<Event, State, Effect>(scope) {

    override fun setInitialState() = State()

    override fun handleEvents(event: Event) {
        when (event) {
            is Init -> init()
            is ShowNextOnboardingItem -> showNextOnboardingItem()
        }
    }

    private fun init() {
        getOnboardingItems()
    }

    private fun getOnboardingItems() = hide {
        appOnboardingManager.getOnboardingData(fromRemote = false).collect { items ->
            if (items.isNotEmpty()) {
                setState { copy(onboardingItems = items, currentOnboardingItem = items.first()) }
            }
        }
    }

    private fun showNextOnboardingItem() = with(viewState.value) {
        val currentOnboardingItemIndex = onboardingItems.indexOfFirst { onboardingItem ->
            onboardingItem == currentOnboardingItem
        }
        if (currentOnboardingItemIndex != -1 && currentOnboardingItemIndex == onboardingItems.lastIndex) {
            setEffect { NavigateToAuth }
        } else {
            val nextOnboardingItem = onboardingItems[currentOnboardingItemIndex + 1]
            setState { copy(currentOnboardingItem = nextOnboardingItem) }
        }
    }
}