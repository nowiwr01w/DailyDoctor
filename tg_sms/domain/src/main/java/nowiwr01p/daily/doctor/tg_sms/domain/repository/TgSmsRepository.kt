package nowiwr01p.daily.doctor.tg_sms.domain.repository

import nowiwr01p.daily.doctor.tg_sms.domain.api.requests.VerificationRequest
import nowiwr01p.daily.doctor.tg_sms.domain.api.response.VerificationResponseWrap

interface TgSmsRepository {
    suspend fun sendVerificationMessage(request: VerificationRequest): VerificationResponseWrap
}