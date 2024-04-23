package ui.common.pin_code

import base.view_model.BaseEffect
import base.view_model.BaseEvent
import base.view_model.BaseState
import ui.common.pin_code.data.PinCodeState
import ui.common.pin_code.data.PinCodeState.DEFAULT

interface PinCodeContract {

    sealed interface Event: BaseEvent {

    }

    data class State(
        val pinCode: String = "",
        val pinCodeState: PinCodeState = DEFAULT
    ): BaseState

    sealed interface Effect: BaseEffect {

    }

    interface Listener {

    }
}