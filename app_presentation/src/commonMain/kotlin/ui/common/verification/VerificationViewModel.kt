package ui.common.verification

import ResendVerificationCodeTimerWork
import base.view_model.BaseViewModel
import com.nowiwr01p.model.api.request.verification.CheckVerificationCodeRequest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
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
    private val checkVerificationCodeUseCode: AppCheckVerificationCodeUseCase,
    private val resendVerificationCodeTimerWork: ResendVerificationCodeTimerWork
): BaseViewModel<Event, State, Effect>(scope) {

    private var verificationTokenFromResend = ""
    private var verificationResendTimerJob: Job? = null

    override fun setInitialState() = State(
        timerSeconds = resendVerificationCodeTimerWork.timerType.startValue
    )

    override fun handleEvents(event: Event) {
        when (event) {
            is Event.Init -> init()
            is Event.OnVerifyClicked -> verify(event.email, event.verificationToken)
            is Event.OnResendCodeClicked -> resend(event.email)
            is Event.HandeUserInput -> handleUserInput(event.operation)
        }
    }

    private fun init() {
        startTimer()
    }

    private fun startTimer() = hide {
        resendVerificationCodeTimerWork.startWork(scope = this) { secondsLeft ->
            setState { copy(timerSeconds = secondsLeft) }
        }.let {
            verificationResendTimerJob = it
        }
    }

    private fun handleUserInput(operation: VerificationEnterCodeOperation) = with(viewState.value) {
        val updatedCode = code.toMutableList().apply {
            set(operation.index, operation.digit)
        }
        setState { copy(code = updatedCode) }
    }

    private fun resend(email: String) = hide {
        runCatching {
            resendVerificationCodeTimerWork.resendCode(email)
        }.onSuccess { token ->
            verificationTokenFromResend = token
            verificationResendTimerJob?.cancel()
            startTimer()
        }
    }

    private fun verify(email: String, token: String) = hide {
        setState { copy(buttonState = SEND_REQUEST) }
        runCatching {
            val checkVerificationCodeRequest = CheckVerificationCodeRequest(
                email = email,
                code = viewState.value.code.joinToString(separator = ""),
                verificationToken = verificationTokenFromResend.ifEmpty { token }
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