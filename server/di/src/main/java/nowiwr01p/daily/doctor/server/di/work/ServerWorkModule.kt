package nowiwr01p.daily.doctor.server.di.work

import nowiwr01p.daily.doctor.server.domain.usecase.verification.ServerDeleteExpiredVerificationCodesUseCase
import nowiwr01p.daily.doctor.server.works.verification.ServerDeleteExpiredVerificationCodesWork
import org.kodein.di.DI
import org.kodein.di.bindSingleton
import org.kodein.di.instance

internal val moduleServerWorks = DI.Module("ServerWorksModule") {
    /**
     * WORK
     */
    bindSingleton {
        ServerDeleteExpiredVerificationCodesWork(
            di = di,
            deleteExpiredVerificationCodesUseCase = instance<ServerDeleteExpiredVerificationCodesUseCase>()
        )
    }
}