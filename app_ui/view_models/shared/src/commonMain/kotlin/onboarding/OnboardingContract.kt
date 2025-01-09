package onboarding

import contract.BaseEffect
import contract.BaseEvent
import contract.BaseState
import com.nowiwr01p.model.model.onboarding.OnboardingItem

sealed interface Event: BaseEvent {
    data object RequestNotifications: Event
    data class ShowNextOnboardingItem(val currentOnboardingItem: OnboardingItem): Event
}

data class State(
    val onboardingItems: List<OnboardingItem> = listOf()
): BaseState

sealed interface Effect: BaseEffect {
    data object NavigateToAuth: Effect
    data object ShowEnableNotificationsDialog: Effect
    data class SlideToNextOnboardingItem(val nextOnboardingItemIndex: Int): Effect
}

interface Listener {
    fun showNextOnboardingItem(currentOnboardingItem: OnboardingItem)
    fun onEnableNotificationsClick()
}
