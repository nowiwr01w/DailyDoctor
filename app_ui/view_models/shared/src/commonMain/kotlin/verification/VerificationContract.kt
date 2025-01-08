package verification

import contract.BaseEffect
import contract.BaseEvent
import contract.BaseState
import components.button.ButtonState
import components.button.ButtonState.DARK_GRAY_ACTIVE

const val VERIFICATION_CODE_LENGTH = 6
const val VERIFICATION_CODE_ANIMATION_DURATION = 500

sealed interface Event: BaseEvent {
    data object OnResendCodeClicked: Event
    data class VerificationCodeInputChanged(val code: String): Event
}

data class State(
    val code: String = "",
    val resendTimerState: ResendTimerState = ResendTimerState(),
    val buttonState: ButtonState = DARK_GRAY_ACTIVE,
    val errorText: String? = null
): BaseState

data class ResendTimerState(
    val isClickEnabled: Boolean = false,
    val text: String = ""
)

sealed interface Effect: BaseEffect {
    data class NavigateToPinCode(val pinCodeToken: String): Effect
    data object OpenKeyboardToEnterCorrectCode: Effect
}

interface Listener {
    fun onResendCodeClicked()
    fun onVerificationCodeInputChange(code: String)
}
