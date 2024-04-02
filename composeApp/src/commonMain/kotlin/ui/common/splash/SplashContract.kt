package ui.common.splash

import base.view_model.BaseEffect
import base.view_model.BaseEvent
import base.view_model.BaseState
import ui.common.splash.SplashContract.SplashAnimationState.ICON

interface SplashContract {

    sealed interface Event: BaseEvent {
        data object Init: Event
    }

    data class State(
        val animationState: SplashAnimationState = ICON
    ): BaseState

    enum class SplashAnimationState(val value: Int) {
        ICON(0),
        FIRST_TEXT(1),
        SECOND_TEXT(2),
        PROGRESS(3)
    }

    sealed interface Effect: BaseEffect {
        data object NavigateToOnboarding: Effect
    }
}