package ui.common.onboarding

import base.view_model.BaseEffect
import base.view_model.BaseEvent
import base.view_model.BaseState
import nowiwr01p.daily.doctor.app_presentation.navigation.onboarding.model.OnboardingItemModel
import ui.common.onboarding.data.OnboardingItem

interface OnboardingContract {
    
    sealed interface Event: BaseEvent {
        data class Init(val onboardingItem: OnboardingItemModel): Event
        data object ShowNextOnboardingItem: Event
    }
    
    data class State(
        val currentOnboardingItem: OnboardingItemModel? = null
    ): BaseState
    
    sealed interface Effect: BaseEffect {
        data object NavigateToAuth: Effect
        data object RequestNotifications: Effect
        data class NavigateToNextOnboardingItem(val onboardingItem: OnboardingItemModel): Effect
    }

    interface Listener {
        fun showNextOnboardingItem()
        fun onEnableNotificationsClick()
    }
}