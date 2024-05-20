import com.nowiwr01p.model.api.request.verification.SendVerificationCodeRequest
import com.nowiwr01p.model.work.timer.TimerType
import com.nowiwr01p.model.work.timer.TimerWork
import usecase.verification.AppSendVerificationCodeUseCase

class ResendVerificationCodeTimerWork(
    private val sendVerificationCodeUseCase: AppSendVerificationCodeUseCase
): TimerWork() {

    private var isResendAvailable = false

    override val timerType = TimerType.Down(startValue = 60)

    override suspend fun onCompletion() {
        isResendAvailable = true
        // TODO: Send analytics with (user_id, verification_end_timer) param
    }

    suspend fun resendCode(email: String) = if (isResendAvailable) {
        val request = SendVerificationCodeRequest(email)
        val token = sendVerificationCodeUseCase.execute(request).token
        isResendAvailable = false
        token
    } else {
        throw IllegalStateException("Resend is unavailable because of timing.")
    }
}