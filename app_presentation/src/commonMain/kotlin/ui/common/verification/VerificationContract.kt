package ui.common.verification

import base.view_model.BaseEffect
import base.view_model.BaseEvent
import base.view_model.BaseState
import ui.common.verification.data.VerificationEnterCodeOperation
import ui.core_ui.components.button.ButtonState
import ui.core_ui.components.button.ButtonState.DEFAULT

interface VerificationContract {

    sealed interface Event: BaseEvent {
        data object Init: Event
        data class OnVerifyClicked(val email: String, val verificationToken: String): Event
        data class OnResendCodeClicked(val email: String): Event
        data class HandeUserInput(val operation: VerificationEnterCodeOperation): Event
    }

    data class State(
        val code: List<String> = listOf("", "", "", "", "", ""),
        val timerSeconds: Long,
        val buttonState: ButtonState = DEFAULT
    ): BaseState

    sealed interface Effect: BaseEffect {
        data object NavigateToPinCode: Effect
    }

    interface Listener {
        fun onVerifyClicked()
        fun onResendCodeClicked()
        fun handeUserInput(operation: VerificationEnterCodeOperation)
    }
}