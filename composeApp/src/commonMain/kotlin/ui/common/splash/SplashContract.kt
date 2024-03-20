package ui.common.splash

import base.view_model.BaseEffect
import base.view_model.BaseEvent
import base.view_model.BaseState

interface SplashContract {

    sealed interface Event: BaseEvent {
        data object Init: Event
    }

    class State: BaseState

    sealed interface Effect: BaseEffect {
        data object NavigateToHome: Effect
    }
}