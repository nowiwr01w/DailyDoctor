package onboarding

import contract.BaseEffect
import contract.BaseEvent
import contract.BaseState
import com.nowiwr01p.model.model.onboarding.OnboardingItem

interface OnboardingContract {
    
    sealed interface Event: BaseEvent {
        data object Init: Event
        data object ShowNextOnboardingItem: Event
    }
    
    data class State(
        val currentOnboardingItem: OnboardingItem? = null,
        val onboardingItems: List<OnboardingItem> = listOf()
    ): BaseState
    
    sealed interface Effect: BaseEffect {
        data object NavigateToAuth: Effect
        data object RequestNotifications: Effect
        data class NavigateToNextOnboardingItem(val onboardingItem: OnboardingItem): Effect
    }

    interface Listener {
        fun showNextOnboardingItem()
        fun onEnableNotificationsClick()
    }
}