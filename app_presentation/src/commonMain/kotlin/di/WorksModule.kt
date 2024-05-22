package di

import ResendVerificationCodeTimerWork
import org.koin.dsl.module
import usecase.verification.AppSendVerificationCodeUseCase

val moduleAppWorks = module {
    /**
     * RESEND VERIFICATION CODE
     */
    factory {
        ResendVerificationCodeTimerWork(
            sendVerificationCodeUseCase = get<AppSendVerificationCodeUseCase>()
        )
    }
}