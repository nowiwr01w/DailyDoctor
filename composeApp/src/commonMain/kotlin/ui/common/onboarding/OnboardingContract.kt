package ui.common.onboarding

import base.view_model.BaseEffect
import base.view_model.BaseEvent
import base.view_model.BaseState
import ui.common.onboarding.data.OnboardingItem

interface OnboardingContract {
    
    sealed interface Event: BaseEvent {
        data object Init: Event
    }
    
    data class State(
        val currentOnboardingItem: OnboardingItem? = null
    ): BaseState
    
    sealed interface Effect: BaseEffect {
        data object RequestNotifications: Effect
    }
    
    interface Listener {
        fun showNextOnboardingItem()
        fun onEnableNotificationsClick()
        fun navigateToAuth()
    }
}