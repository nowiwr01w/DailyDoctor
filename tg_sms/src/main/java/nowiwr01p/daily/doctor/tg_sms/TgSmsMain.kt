package nowiwr01p.daily.doctor.tg_sms

import com.nowiwr01p.model.di.initKoin
import kotlinx.coroutines.runBlocking
import nowiwr01p.daily.doctor.tg_sms.di.tgSmsModules
import nowiwr01p.daily.doctor.tg_sms.domain.api.requests.VerificationRequest
import nowiwr01p.daily.doctor.tg_sms.domain.usecase.TgSendVerificationCodeUseCase
import org.koin.core.KoinApplication
import org.koin.core.qualifier.named

fun main() {
    initKoin(
        modules = tgSmsModules,
        platformCallback = { sendVerificationMessage() }
    )
}

private fun KoinApplication.sendVerificationMessage() {
    val phoneNumber = koin.get<String>(named("my_telegram_phone_number"))
    val sendVerificationCodeUseCase = koin.get<TgSendVerificationCodeUseCase>()
    runBlocking {
        runCatching {
            val request = getVerificationRequest(phoneNumber)
            sendVerificationCodeUseCase.execute(request)
        }
    }
}

private fun getVerificationRequest(phoneNumber: String) = VerificationRequest(
    phoneNumber = phoneNumber,
    code = "2281337",
    expiredAfterSeconds = 60
)
