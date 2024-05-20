import com.nowiwr01p.model.work.timer.TimerType
import com.nowiwr01p.model.work.timer.TimerWork

class ResendVerificationCodeCountDownWork: TimerWork() {

    override val timerType = TimerType.Down(startValue = 60L)

    override suspend fun onCompletion() {
        // TODO: Send analytics with (user_id, verification_end_timer) param
    }
}