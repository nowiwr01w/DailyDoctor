package nowiwr01p.daily.doctor.server.di.work

import nowiwr01p.daily.doctor.server.domain.usecase.verification.ServerDeleteExpiredVerificationCodesUseCase
import nowiwr01p.daily.doctor.server.works.verification.ServerDeleteExpiredVerificationCodesWork
import org.koin.dsl.module

internal val moduleServerWorks = module {
    /**
     * WORK
     */
    single {
        ServerDeleteExpiredVerificationCodesWork(
            deleteExpiredVerificationCodesUseCase = get<ServerDeleteExpiredVerificationCodesUseCase>()
        )
    }
}