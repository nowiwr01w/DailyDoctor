package verification

import ResendVerificationCodeTimerWork
import com.nowiwr01p.model.api.request.verification.CheckVerificationCodeRequest
import com.nowiwr01p.model.extensions.runCatchingApp
import components.button.ButtonState.DARK_GRAY_ACTIVE
import components.button.ButtonState.DARK_GRAY_PROGRESS
import components.button.ButtonState.ERROR
import components.button.ButtonState.SUCCESS
import extensions.format
import extensions.withLeadingZero
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import nowiwr01p.daily.doctor.new_resources.component_with_resources.screens.verification.VerificationScreenResources
import pro.respawn.flowmvi.api.PipelineContext
import usecase.verification.AppCheckVerificationCodeUseCase
import verification.Effect.OpenKeyboardToEnterCorrectCode
import verification.Event.OnResendCodeClicked
import verification.Event.VerificationCodeInputChanged
import view_model.BaseViewModel

private typealias Ctx = PipelineContext<State, Event, Effect>

class VerificationViewModel(
    private val phone: String,
    private val verificationTokenFromAuth: String,
    private val screenResources: VerificationScreenResources,
    private val checkVerificationCodeUseCode: AppCheckVerificationCodeUseCase,
    private val resendVerificationCodeTimerWork: ResendVerificationCodeTimerWork
): BaseViewModel<State, Event, Effect>(
    initialValue = State()
) {
    private var verificationTokenFromResend = ""
    private var verificationResendTimerJob: Job? = null
    /**
     * INIT
     */
    override suspend fun Ctx.handleEvents(event: Event) {
        when (event) {
            is OnResendCodeClicked -> resend()
            is VerificationCodeInputChanged -> handleVerificationCodeInputChanges(event.code)
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
            withState {
                val text = when {
                    secondsLeft == 0 -> screenResources.verificationNewCodeRequired
                    else -> screenResources.verificationSendCodeAgain.format(secondsLeft.withLeadingZero())
                }
                val updatedResendTimerState = resendTimerState.copy(
                    text = text,
                    isClickEnabled = secondsLeft == 0
                )
                setState { copy(resendTimerState = updatedResendTimerState) }
            }
        }
    }

    /**
     * USER INPUT
     */
    private suspend fun Ctx.handleVerificationCodeInputChanges(code: String) = withState {
        setState { copy(code = code, errorText = null) }
        if (code.length == VERIFICATION_CODE_LENGTH) {
            verify(code)
        }
    }

    /**
     * VERIFICATION
     */
    private fun Ctx.verify(code: String) = scope.launch {
        withState {
            setState { copy(buttonState = DARK_GRAY_PROGRESS) }
            delay(1500)
            runCatchingApp {
                val checkVerificationCodeRequest = CheckVerificationCodeRequest(
                    code = code,
                    phone = phone,
                    verificationToken = verificationTokenFromResend.ifEmpty { verificationTokenFromAuth }
                )
                checkVerificationCodeUseCode.execute(checkVerificationCodeRequest)
            }.onSuccess { pinCodeTokenResponse ->
                setState { copy(buttonState = SUCCESS) }
                delay(3000)
                setEffect(Effect.NavigateToPinCode(pinCodeTokenResponse.token))
            }.onFailure {
                setState { copy(buttonState = ERROR, errorText = it.message) }
                delay(3000)
                setState { copy(buttonState = DARK_GRAY_ACTIVE, code = "") }
                setEffect(OpenKeyboardToEnterCorrectCode)
            }
        }
    }

    private fun Ctx.resend() = io {
        runCatchingApp {
            resendVerificationCodeTimerWork.resendCode(phone)
        }.onSuccess { token ->
            verificationTokenFromResend = token
            verificationResendTimerJob?.cancel()
            startTimer()
        }
    }
}
