package nowiwr01p.daily.doctor.server.main.work.works

import com.nowiwr01p.model.time.TimeInSeconds
import com.nowiwr01p.model.usecase.execute
import com.nowiwr01p.model.work.periodic.PeriodicWork
import nowiwr01p.daily.doctor.server.domain.usecase.verification.ServerDeleteExpiredVerificationCodesUseCase
import org.kodein.di.DI

class ServerDeleteExpiredVerificationCodesWork(
    di: DI,
    private val deleteExpiredVerificationCodesUseCase: ServerDeleteExpiredVerificationCodesUseCase
): PeriodicWork(di) {

    override val periodType = TimeInSeconds.PeriodMinutes(minutes = 1)

    override suspend fun onEach(seconds: Long) {
        runCatching {
            deleteExpiredVerificationCodesUseCase.execute()
        }
    }
}