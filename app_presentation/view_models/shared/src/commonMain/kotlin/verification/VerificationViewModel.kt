package verification

import ResendVerificationCodeTimerWork
import com.nowiwr01p.model.api.request.verification.CheckVerificationCodeRequest
import com.nowiwr01p.model.extensions.runCatchingApp
import components.button.ButtonState.DARK_GRAY_ACTIVE
import components.button.ButtonState.DARK_GRAY_PROGRESS
import components.button.ButtonState.ERROR
import components.button.ButtonState.SUCCESS
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import pro.respawn.flowmvi.api.PipelineContext
import usecase.verification.AppCheckVerificationCodeUseCase
import verification.Event.HandeUserInput
import verification.Event.OnResendCodeClicked
import verification.Event.OnVerifyClicked
import verification.data.VerificationEnterCodeOperation
import view_model.BaseViewModel

private typealias Ctx = PipelineContext<State, Event, Effect>

class VerificationViewModel(
    private val checkVerificationCodeUseCode: AppCheckVerificationCodeUseCase,
    private val resendVerificationCodeTimerWork: ResendVerificationCodeTimerWork
): BaseViewModel<State, Event, Effect>(
    initialValue = State(timerSeconds = resendVerificationCodeTimerWork.timerType.startValue)
) {
    private var verificationTokenFromResend = ""
    private var verificationResendTimerJob: Job? = null

    /**
     * INIT
     */
    override suspend fun Ctx.handleEvents(event: Event) {
        when (event) {
            is OnVerifyClicked -> verify(event.phone, event.verificationToken)
            is OnResendCodeClicked -> resend(event.phone)
            is HandeUserInput -> handleUserInput(event.operation)
        }
    }

    override suspend fun Ctx.init() {
        startTimer()
    }

    /**
     * TIMER
     */
    private fun Ctx.startTimer() = io {
        verificationResendTimerJob = resendVerificationCodeTimerWork.startWork(scope = this) { secondsLeft ->
            setState { copy(timerSeconds = secondsLeft) }
        }
    }

    /**
     * USER INPUT
     */
    private suspend fun Ctx.handleUserInput(operation: VerificationEnterCodeOperation) = withState {
        val updatedCode = code.toMutableList().apply {
            set(operation.index, operation.digit)
        }
        setState { copy(code = updatedCode) }
    }

    /**
     * VERIFICATION
     */
    private fun Ctx.verify(phone: String, token: String) = io {
        withState {
            setState { copy(buttonState = DARK_GRAY_PROGRESS) }
            runCatchingApp {
                val checkVerificationCodeRequest = CheckVerificationCodeRequest(
                    phone = phone,
                    code = code.joinToString(separator = ""),
                    verificationToken = verificationTokenFromResend.ifEmpty { token }
                )
                checkVerificationCodeUseCode.execute(checkVerificationCodeRequest)
            }.onSuccess { pinCodeTokenResponse ->
                setState { copy(buttonState = SUCCESS) }
                delay(3000)
                setEffect(Effect.NavigateToPinCode(pinCodeTokenResponse.token))
            }.onFailure {
                setState { copy(buttonState = ERROR) }
                delay(3000)
                setState { copy(buttonState = DARK_GRAY_ACTIVE) }
            }
        }
    }

    private fun Ctx.resend(phone: String) = io {
        runCatchingApp {
            resendVerificationCodeTimerWork.resendCode(phone)
        }.onSuccess { token ->
            verificationTokenFromResend = token
            verificationResendTimerJob?.cancel()
            startTimer()
        }
    }
}
