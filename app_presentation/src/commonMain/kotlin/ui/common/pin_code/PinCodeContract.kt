package ui.common.pin_code

import base.view_model.BaseEffect
import base.view_model.BaseEvent
import base.view_model.BaseState
import nowiwr01p.daily.doctor.app_presentation.navigation.pin_code.model.PinCodeMode
import nowiwr01p.daily.doctor.app_presentation.navigation.pin_code.model.PinCodeMode.*
import ui.common.pin_code.data.PinCodeOperation
import ui.common.pin_code.data.PinCodeState
import ui.common.pin_code.data.PinCodeState.DEFAULT

interface PinCodeContract {

    sealed interface Event: BaseEvent {
        data class Init(val mode: PinCodeMode): Event
        data class HandleUserInput(val operation: PinCodeOperation): Event
    }

    data class State(
        val pinCode: String = "",
        val pinCodeMode: PinCodeMode = CREATE,
        val pinCodeState: PinCodeState = DEFAULT
    ): BaseState

    sealed interface Effect: BaseEffect {
        data object NavigateBack: Effect
        data object NavigateToHome: Effect
    }

    interface Listener {
        fun handleUserInput(operation: PinCodeOperation)
        fun requestBiometric()
    }
}