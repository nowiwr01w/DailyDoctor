package pin_code

import contract.BaseEffect
import contract.BaseEvent
import contract.BaseState
import nowiwr01p.daily.doctor.app_ui.navigation.model.pin.PinCodeMode
import pin_code.data.PinCodeOperation

/**
 * EVENT
 */
sealed interface Event: BaseEvent {
    data class HandleUserInput(val operation: PinCodeOperation): Event
}

/**
 * STATE
 */
data class State(
    val mode: PinCodeMode,
    val title: String = "",
    val isUserProfileImageVisible: Boolean = false,
    val pinCode: String = "",
    val pinCodeState: PinCodeState = PinCodeState.Default
): BaseState

sealed interface PinCodeState {
    data object Default: PinCodeState
    data object SendRequest: PinCodeState
    data object Success: PinCodeState
    data class Error(val error: PinCodeError): PinCodeState
}

sealed class PinCodeError(override val message: String): Throwable(message) {
    data class OneTryLeftError(override val message: String): PinCodeError(message)
    data class PasscodesDoNotMatch(override val message: String): PinCodeError(message)
    data class UnexpectedError(override val message: String): PinCodeError(message)
}

/**
 * EFFECT
 */
sealed interface Effect: BaseEffect {
    data object NavigateBack: Effect
    data object NavigateToHome: Effect
}

/**
 * LISTENER
 */
interface Listener {
    fun handleUserInput(operation: PinCodeOperation)
    fun requestBiometric()
}
