package verification

import ResendVerificationCodeTimerWork
import view_model.BaseViewModel
import com.nowiwr01p.model.api.request.verification.CheckVerificationCodeRequest
import com.nowiwr01p.model.extensions.runCatchingApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import verification.VerificationContract.Effect
import verification.VerificationContract.Event
import verification.VerificationContract.State
import verification.data.VerificationEnterCodeOperation
import components.button.ButtonState.DEFAULT
import components.button.ButtonState.ERROR
import components.button.ButtonState.SEND_REQUEST
import components.button.ButtonState.SUCCESS
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
            is Event.OnVerifyClicked -> verify(event.phone, event.verificationToken)
            is Event.OnResendCodeClicked -> resend(event.phone)
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

    private fun resend(phone: String) = hide {
        runCatchingApp {
            resendVerificationCodeTimerWork.resendCode(phone)
        }.onSuccess { token ->
            verificationTokenFromResend = token
            verificationResendTimerJob?.cancel()
            startTimer()
        }
    }

    private fun verify(phone: String, token: String) = hide {
        setState { copy(buttonState = SEND_REQUEST) }
        runCatchingApp {
            val checkVerificationCodeRequest = CheckVerificationCodeRequest(
                phone = phone,
                code = viewState.value.code.joinToString(separator = ""),
                verificationToken = verificationTokenFromResend.ifEmpty { token }
            )
            checkVerificationCodeUseCode.execute(checkVerificationCodeRequest)
        }.onSuccess { pinCodeTokenResponse ->
            setState { copy(buttonState = SUCCESS) }
            delay(3000)
            setEffect { Effect.NavigateToPinCode(pinCodeTokenResponse.token) }
        }.onFailure {
            setState { copy(buttonState = ERROR) }
            delay(3000)
            setState { copy(buttonState = DEFAULT) }
        }
    }
}