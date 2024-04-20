package ui.common.onboarding

import base.view_model.BaseViewModel
import kotlinx.coroutines.CoroutineScope
import ui.common.onboarding.OnboardingContract.Effect
import ui.common.onboarding.OnboardingContract.Event
import ui.common.onboarding.OnboardingContract.State
import ui.common.onboarding.data.OnboardingItem
import ui.common.onboarding.data.getOnboardingItems

class OnboardingViewModel(scope: CoroutineScope) : BaseViewModel<Event, State, Effect>(scope) {

    override fun setInitialState() = State()

    override fun handleEvents(event: Event) {
        when (event) {
            is Event.Init -> init(event.onboardingItem)
            is Event.ShowNextOnboardingItem -> showNextOnboardingItem()
        }
    }

    private fun init(onboardingItem: OnboardingItem) {
        setState { copy(currentOnboardingItem = onboardingItem) }
    }

    private fun showNextOnboardingItem() = with(viewState.value) {
        val onboardingItems = getOnboardingItems()
        val currentOnboardingItemIndex = onboardingItems.indexOf(currentOnboardingItem)
        if (currentOnboardingItemIndex != -1) {
            when {
                currentOnboardingItemIndex == onboardingItems.lastIndex -> setEffect {
                    Effect.NavigateToAuth
                }
                else -> setEffect {
                    val nextOnboardingItem = onboardingItems[currentOnboardingItemIndex + 1]
                    Effect.NavigateToNextOnboardingItem(nextOnboardingItem)
                }
            }
        }
    }
}