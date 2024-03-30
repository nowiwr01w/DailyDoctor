package ui.common.onboarding

import base.view_model.BaseViewModel
import kotlinx.coroutines.CoroutineScope
import ui.common.onboarding.OnboardingContract.Effect
import ui.common.onboarding.OnboardingContract.Event
import ui.common.onboarding.OnboardingContract.State
import ui.common.onboarding.data.getOnboardingItems

class OnboardingViewModel(scope: CoroutineScope) : BaseViewModel<Event, State, Effect>(scope) {

    override fun setInitialState() = State()

    override fun handleEvents(event: Event) {
        when (event) {
            is Event.Init -> init()
        }
    }

    private fun init() {
        val currentOnboardingItem = getOnboardingItems().first()
        setState { copy(currentOnboardingItem = currentOnboardingItem) }
    }
}