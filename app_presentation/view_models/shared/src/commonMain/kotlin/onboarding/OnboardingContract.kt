package onboarding

import contract.BaseEffect
import contract.BaseEvent
import contract.BaseState
import nowiwr01p.daily.doctor.app_presentation.navigation.onboarding.model.OnboardingItemModel

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