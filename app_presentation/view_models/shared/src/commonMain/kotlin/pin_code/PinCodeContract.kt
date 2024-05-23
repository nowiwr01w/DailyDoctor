package pin_code

import contract.BaseEffect
import contract.BaseEvent
import contract.BaseState
import nowiwr01p.daily.doctor.app_presentation.navigation.pin_code.model.PinCodeMode
import nowiwr01p.daily.doctor.app_presentation.navigation.pin_code.model.PinCodeMode.*
import pin_code.data.PinCodeOperation
import pin_code.data.PinCodeState
import pin_code.data.PinCodeState.DEFAULT

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