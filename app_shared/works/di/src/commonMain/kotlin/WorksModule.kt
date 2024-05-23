package di.works

import ResendVerificationCodeTimerWork
import org.koin.dsl.module
import usecase.verification.AppSendVerificationCodeUseCase

val moduleAppSharedWorks = module {
    /**
     * RESEND VERIFICATION CODE
     */
    factory {
        ResendVerificationCodeTimerWork(
            sendVerificationCodeUseCase = get<AppSendVerificationCodeUseCase>()
        )
    }
}