package ui.common.splash

import base.view_model.BaseEffect
import base.view_model.BaseEvent
import base.view_model.BaseState
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