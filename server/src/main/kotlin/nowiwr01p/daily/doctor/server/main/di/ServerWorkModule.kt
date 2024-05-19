package nowiwr01p.daily.doctor.server.main.di

import nowiwr01p.daily.doctor.server.domain.usecase.verification.ServerDeleteExpiredVerificationCodesUseCase
import nowiwr01p.daily.doctor.server.main.work.works.ServerDeleteExpiredVerificationCodesWork
import org.kodein.di.DI
import org.kodein.di.bindSingleton
import org.kodein.di.instance

val moduleServerWork = DI.Module("ServerWork") {
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