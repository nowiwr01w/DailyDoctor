package nowiwr01p.daily.doctor.server.works.verification

import com.nowiwr01p.model.extensions.runCatchingApp
import com.nowiwr01p.model.time.TimeInSeconds
import com.nowiwr01p.model.usecase.execute
import com.nowiwr01p.model.work.periodic.PeriodicWork
import nowiwr01p.daily.doctor.server.domain.usecase.verification.ServerDeleteExpiredVerificationCodesUseCase

class ServerDeleteExpiredVerificationCodesWork(
    private val deleteExpiredVerificationCodesUseCase: ServerDeleteExpiredVerificationCodesUseCase
): PeriodicWork() {

    override val periodType = TimeInSeconds.PeriodMinutes(minutes = 1)

    override suspend fun onEach(seconds: Long) {
        runCatchingApp {
            deleteExpiredVerificationCodesUseCase.execute()
        }
    }
}