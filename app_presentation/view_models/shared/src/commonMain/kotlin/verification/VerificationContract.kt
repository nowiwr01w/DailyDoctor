package verification

import contract.BaseEffect
import contract.BaseEvent
import contract.BaseState
import verification.data.VerificationEnterCodeOperation
import components.button.ButtonState
import components.button.ButtonState.DARK_GRAY_ACTIVE

sealed interface Event: BaseEvent {
    data class OnVerifyClicked(val phone: String, val verificationToken: String): Event
    data class OnResendCodeClicked(val phone: String): Event
    data class HandeUserInput(val operation: VerificationEnterCodeOperation): Event
}

data class State(
    val code: List<String> = listOf("", "", "", "", "", ""),
    val timerSeconds: Long,
    val buttonState: ButtonState = DARK_GRAY_ACTIVE
): BaseState

sealed interface Effect: BaseEffect {
    data class NavigateToPinCode(val pinCodeToken: String): Effect
}

interface Listener {
    fun onVerifyClicked()
    fun onResendCodeClicked()
    fun handeUserInput(operation: VerificationEnterCodeOperation)
}
