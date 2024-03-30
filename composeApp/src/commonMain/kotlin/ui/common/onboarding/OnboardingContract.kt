package ui.common.onboarding

import base.view_model.BaseEffect
import base.view_model.BaseEvent
import base.view_model.BaseState
import ui.common.onboarding.data.OnboardingItem

interface OnboardingContract {
    
    sealed interface Event: BaseEvent {
        data class Init(val onboardingItem: OnboardingItem): Event
        data object ShowNextOnboardingItem: Event
    }
    
    data class State(
        val currentOnboardingItem: OnboardingItem? = null
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