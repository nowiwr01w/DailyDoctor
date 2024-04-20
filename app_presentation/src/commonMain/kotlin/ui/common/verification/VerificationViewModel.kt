package ui.common.verification

import base.view_model.BaseViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import ui.common.verification.VerificationContract.Effect
import ui.common.verification.VerificationContract.Event
import ui.common.verification.VerificationContract.State
import ui.common.verification.data.VerificationEnterCodeOperation

class VerificationViewModel(scope: CoroutineScope): BaseViewModel<Event, State, Effect>(scope) {

    override fun setInitialState() = State()

    override fun handleEvents(event: Event) {
        when (event) {
            is Event.Init -> init()
            is Event.OnVerifyClicked -> verify()
            is Event.OnResendCodeClicked -> resend()
            is Event.HandeUserInput -> handleUserInput(event.operation)
        }
    }

    private fun init() {
        startTimer()
    }

    private fun startTimer() = hide {
        (TIMER_RESEND_VERIFICATION_SECONDS downTo 0 step 1).asSequence()
            .asFlow()
            .onEach { secondsLeft ->
                setState { copy(timerSeconds = secondsLeft) }
                delay(1000)
            }
            .onCompletion {
                // TODO: Send analytics with (user_id, verification_end_timer) param
            }
            .collect()
    }

    private fun handleUserInput(operation: VerificationEnterCodeOperation) = with(viewState.value) {
        val updatedCode = code.toMutableList().apply {
            set(operation.index, operation.digit)
        }
        setState { copy(code = updatedCode) }
    }

    private fun resend() = hide {
        runCatching {

        }.onSuccess {
            startTimer()
        }
    }

    private fun verify() = hide {
        runCatching {

        }.onSuccess {

        }
    }
}