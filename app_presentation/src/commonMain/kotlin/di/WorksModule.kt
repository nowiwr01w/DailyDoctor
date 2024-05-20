package di

import ResendVerificationCodeCountDownWork
import org.kodein.di.DI
import org.kodein.di.bindProvider

val moduleAppWorks = DI.Module("AppWorksModule") {
    /**
     * RESEND VERIFICATION CODE
     */
    bindProvider {
        ResendVerificationCodeCountDownWork()
    }
}