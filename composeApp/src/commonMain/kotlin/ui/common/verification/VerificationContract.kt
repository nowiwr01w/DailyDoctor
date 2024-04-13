package ui.common.verification

import base.view_model.BaseEffect
import base.view_model.BaseEvent
import base.view_model.BaseState
import ui.common.verification.data.VerificationEnterCodeOperation
import ui.core_ui.components.ButtonState
import ui.core_ui.components.ButtonState.DEFAULT

internal const val TIMER_RESEND_VERIFICATION_SECONDS = 60

interface VerificationContract {

    sealed interface Event: BaseEvent {
        data object Init: Event
        data object OnVerifyClicked: Event
        data object OnResendCodeClicked: Event
        data class HandeUserInput(val operation: VerificationEnterCodeOperation): Event
    }

    data class State(
        val code: List<String> = listOf("", "", "", "", "", ""),
        val timerSeconds: Int = TIMER_RESEND_VERIFICATION_SECONDS,
        val buttonState: ButtonState = DEFAULT
    ): BaseState

    sealed interface Effect: BaseEffect {
        data object NavigateToHome: Effect
    }

    interface Listener {
        fun onVerifyClicked()
        fun onResendCodeClicked()
        fun handeUserInput(operation: VerificationEnterCodeOperation)
    }
}