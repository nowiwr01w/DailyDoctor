package ui.common.splash

import contract.BaseEffect
import contract.BaseEvent
import contract.BaseState
import ui.common.splash.data.SplashAnimationState
import ui.common.splash.data.SplashAnimationState.ICON

interface SplashContract {

    sealed interface Event: BaseEvent {
        data object Init: Event
    }

    data class State(
        val animationState: SplashAnimationState = ICON
    ): BaseState

    sealed interface Effect: BaseEffect {
        data object NavigateToOnboarding: Effect
    }
}