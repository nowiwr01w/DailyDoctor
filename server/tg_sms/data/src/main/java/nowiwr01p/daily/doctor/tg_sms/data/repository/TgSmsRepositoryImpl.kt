package nowiwr01p.daily.doctor.tg_sms.data.repository

import com.nowiwr01p.model.repository.BaseRepository
import nowiwr01p.daily.doctor.tg_sms.domain.api.TgSmsApi
import nowiwr01p.daily.doctor.tg_sms.domain.api.requests.VerificationRequest
import nowiwr01p.daily.doctor.tg_sms.domain.repository.TgSmsRepository

class TgSmsRepositoryImpl(
    private val api: TgSmsApi
): BaseRepository(), TgSmsRepository {

    override suspend fun sendVerificationMessage(request: VerificationRequest) = run {
        api.sendVerificationCode(request)
    }
}