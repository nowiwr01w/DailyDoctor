package pin_code

import components.button.ButtonState
import contract.BaseEffect
import contract.BaseEvent
import contract.BaseState
import nowiwr01p.daily.doctor.app_presentation.navigation.pin_code.model.PinCodeMode
import pin_code.data.PinCodeOperation

interface PinCodeContract {

    sealed interface Event: BaseEvent {
        data class HandleUserInput(val operation: PinCodeOperation): Event
    }

    data class State(
        val pinCode: String = "",
        val pinCodeMode: PinCodeMode,
        val buttonState: ButtonState = ButtonState.DEFAULT
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