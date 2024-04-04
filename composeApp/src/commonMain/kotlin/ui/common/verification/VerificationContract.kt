package ui.common.verification

import base.view_model.BaseEffect
import base.view_model.BaseEvent
import base.view_model.BaseState

internal const val TIMER_RESEND_VERIFICATION_SECONDS = 60

interface VerificationContract {

    sealed interface Event: BaseEvent {
        data object Init: Event
    }

    data class State(
        val timerSeconds: Int = TIMER_RESEND_VERIFICATION_SECONDS
    ): BaseState

    sealed interface Effect: BaseEffect {

    }

    interface Listener {

    }
}