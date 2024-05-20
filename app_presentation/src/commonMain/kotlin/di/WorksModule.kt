package di

import ResendVerificationCodeTimerWork
import org.kodein.di.DI
import org.kodein.di.bindProvider
import org.kodein.di.instance
import usecase.verification.AppSendVerificationCodeUseCase

val moduleAppWorks = DI.Module("AppWorksModule") {
    /**
     * RESEND VERIFICATION CODE
     */
    bindProvider {
        ResendVerificationCodeTimerWork(
            sendVerificationCodeUseCase = instance<AppSendVerificationCodeUseCase>()
        )
    }
}