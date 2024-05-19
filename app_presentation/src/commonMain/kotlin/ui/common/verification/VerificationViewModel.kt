package ui.common.verification

import base.view_model.BaseViewModel
import com.nowiwr01p.model.api.request.verification.CheckVerificationCodeRequest
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
import ui.core_ui.components.button.ButtonState.DEFAULT
import ui.core_ui.components.button.ButtonState.ERROR
import ui.core_ui.components.button.ButtonState.SEND_REQUEST
import ui.core_ui.components.button.ButtonState.SUCCESS
import usecase.verification.AppCheckVerificationCodeUseCase

class VerificationViewModel(
    scope: CoroutineScope,
    private val checkVerificationCodeUseCode: AppCheckVerificationCodeUseCase
): BaseViewModel<Event, State, Effect>(scope) {

    override fun setInitialState() = State()

    override fun handleEvents(event: Event) {
        when (event) {
            is Event.Init -> init()
            is Event.OnVerifyClicked -> verify(event.email, event.verificationToken)
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

    private fun verify(email: String, verificationToken: String) = hide {
        setState { copy(buttonState = SEND_REQUEST) }
        runCatching {
            val checkVerificationCodeRequest = CheckVerificationCodeRequest(
                email = email,
                code = viewState.value.code.joinToString(separator = ""),
                verificationToken = verificationToken
            )
            checkVerificationCodeUseCode.execute(checkVerificationCodeRequest)
        }.onSuccess {
            setState { copy(buttonState = SUCCESS) }
            delay(3000)
            setEffect { Effect.NavigateToPinCode }
        }.onFailure {
            setState { copy(buttonState = ERROR) }
            delay(3000)
            setState { copy(buttonState = DEFAULT) }
        }
    }
}