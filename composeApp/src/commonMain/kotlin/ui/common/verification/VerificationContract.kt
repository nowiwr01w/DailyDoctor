package ui.common.verification

import base.view_model.BaseEffect
import base.view_model.BaseEvent
import base.view_model.BaseState

interface VerificationContract {

    sealed interface Event: BaseEvent {

    }

    class State: BaseState

    sealed interface Effect: BaseEffect {

    }

    interface Listener {

    }
}