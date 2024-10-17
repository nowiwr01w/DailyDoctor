package nowiwr01p.daily.doctor.tg_sms.data.usecase

import nowiwr01p.daily.doctor.tg_sms.domain.api.requests.VerificationRequest
import nowiwr01p.daily.doctor.tg_sms.domain.api.response.VerificationResponseWrap
import nowiwr01p.daily.doctor.tg_sms.domain.repository.TgSmsRepository
import nowiwr01p.daily.doctor.tg_sms.domain.usecase.TgSendVerificationCodeUseCase

class TgSendVerificationCodeUseCaseImpl(
    private val repository: TgSmsRepository
): TgSendVerificationCodeUseCase {

    override suspend fun execute(input: VerificationRequest): VerificationResponseWrap {
        return repository.sendVerificationMessage(input)
    }
}