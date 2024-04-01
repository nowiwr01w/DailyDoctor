package ui.common.auth

import base.view_model.BaseEffect
import base.view_model.BaseEvent
import base.view_model.BaseState

interface AuthContract {

    sealed interface Event: BaseEvent {

    }

    class State: BaseState

    sealed interface Effect: BaseEffect {

    }

    interface Listener {

    }
}