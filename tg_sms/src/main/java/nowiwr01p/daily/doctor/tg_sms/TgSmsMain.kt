package nowiwr01p.daily.doctor.tg_sms

import com.nowiwr01p.model.di.initKoin
import kotlinx.coroutines.runBlocking
import nowiwr01p.daily.doctor.tg_sms.di.tgSmsModules
import nowiwr01p.daily.doctor.tg_sms.domain.api.requests.VerificationRequest
import nowiwr01p.daily.doctor.tg_sms.domain.usecase.TgSendVerificationCodeUseCase

fun main() {
    initKoin(
        modules = tgSmsModules,
        platformCallback = {
            val sendVerificationCodeUseCase = koin.get<TgSendVerificationCodeUseCase>()
            sendVerificationMessage(sendVerificationCodeUseCase)
        }
    )
}

private fun sendVerificationMessage(useCase: TgSendVerificationCodeUseCase) = runBlocking {
    runCatching {
        useCase.execute(getVerificationRequest())
    }
}

private fun getVerificationRequest() = VerificationRequest(
    phoneNumber = "+xxx",
    code = "2281337",
    expiredAfterSeconds = 60
)
