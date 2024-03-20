package ui.common.splash

import base.view_model.BaseEffect
import base.view_model.BaseEvent
import base.view_model.BaseState
import ui.common.splash.SplashContract.SplashAnimationState.PROGRESS

interface SplashContract {

    sealed interface Event: BaseEvent {
        data object Init: Event
    }

    data class State(
        val animationState: SplashAnimationState = PROGRESS
    ): BaseState

    enum class SplashAnimationState(val value: Int) {
        PROGRESS(0),
        ICON(1),
        FIRST_TEXT(2),
        SECOND_TEXT(3)
    }

    sealed interface Effect: BaseEffect {
        data object NavigateToHome: Effect
    }
}