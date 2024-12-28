package pin_code

import components.button.ButtonState
import contract.BaseEffect
import contract.BaseEvent
import contract.BaseState
import nowiwr01p.daily.doctor.app_presentation.navigation.model.pin.PinCodeMode
import pin_code.data.PinCodeOperation

sealed interface Event: BaseEvent {
    data class HandleUserInput(val operation: PinCodeOperation): Event
}

data class State(
    val pinCode: String = "",
    val pinCodeMode: PinCodeMode, // TODO: navigation.model
    val buttonState: ButtonState = ButtonState.DARK_GRAY_ACTIVE
): BaseState

sealed interface Effect: BaseEffect {
    data object NavigateBack: Effect
    data object NavigateToHome: Effect
}

interface Listener {
    fun handleUserInput(operation: PinCodeOperation)
    fun requestBiometric()
}
