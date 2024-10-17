package nowiwr01p.daily.doctor.tg_sms.domain.api

import nowiwr01p.daily.doctor.base_api_client.api.Api
import nowiwr01p.daily.doctor.tg_sms.domain.api.requests.VerificationRequest
import nowiwr01p.daily.doctor.tg_sms.domain.api.response.VerificationResponseWrap

interface TgSmsApi: Api {
    suspend fun sendVerificationCode(request: VerificationRequest): VerificationResponseWrap
}